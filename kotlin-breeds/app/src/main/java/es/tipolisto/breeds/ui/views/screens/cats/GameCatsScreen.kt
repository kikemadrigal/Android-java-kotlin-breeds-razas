package es.tipolisto.breeds.ui.views.screens.cats


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.components.MyCircularProgressIndicator
import es.tipolisto.breeds.ui.components.onBackPressed
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.utils.AudioEffectsType
import es.tipolisto.breeds.utils.MediaPlayerClient
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCatScreen(navController: NavController, catsViewModel:CatsViewModel, mediaPlayerClient: MediaPlayerClient) {
    val context= LocalContext.current
    //Esto es para que solo se ejecute 1 vez
    LaunchedEffect(key1 = true){
        if (!catsViewModel.justOnce) {
            catsViewModel.loadAndInsertBuffer()
            catsViewModel.get3RamdomCats()
            catsViewModel.justOnce=true
        }
    }
    //Conrol del botón atrás
    BackHandler{
        catsViewModel.initGame()
        onBackPressed(navController,context)
    }

    val isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold (
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.cat_game_playing_cat_breeds))
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon={
                        IconButton(
                            onClick = {
                                catsViewModel.initGame()
                                onBackPressed(navController,context)
                            }
                        ){
                            Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if(catsViewModel.getAll().isEmpty()){
                                Toast.makeText(context,"Empty list",Toast.LENGTH_LONG).show()
                            }else {
                                navController.navigate(AppScreens.ListCatsScreen.route)
                            }
                        })
                        {
                            Image(painter = painterResource(id = R.drawable.cat), contentDescription = "Cat list")
                        }
                    }
                )
            }
        ){
            GameCatScreenContent(it,catsViewModel, navController, mediaPlayerClient)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameCatScreen() {
    BreedsTheme (darkTheme = false){
        //GameCatScreen()
    }
}

@Composable
fun GameCatScreenContent(paddingsValues:PaddingValues, catsViewModel:CatsViewModel, navController: NavController, mediaPlayerClient: MediaPlayerClient){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingsValues),
        horizontalAlignment = Alignment.CenterHorizontally
        //verticalArrangement = Arrangement.Center
    ){
        if(catsViewModel.stateIsloading){
            MyCircularProgressIndicator(isDisplayed = true)
        }else
            MyCircularProgressIndicator(isDisplayed = false)
        if(catsViewModel.gameOver) {
            catsViewModel.initGame()
            navController.popBackStack()
            navController.navigate(AppScreens.MenuScreen.route)
        }else{
            //catsViewModel.initGame()
            Hud(catsViewModel)
            //Pintamos la imagen del gato activo
            DrawImageCat(catsViewModel)
            //Pondreos el resultado a verdadero o falso
            CatTest(catsViewModel, mediaPlayerClient)
        }
    }
}




@Composable
fun Hud(catsViewModel: CatsViewModel){
    val lives=catsViewModel.state.lives
    val score=catsViewModel.state.score
    Row (
        modifier= Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(
                1.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.secondary
            ),
        horizontalArrangement = Arrangement.Center
    ){
        //Mira las tipografías aki: https://m3.material.io/styles/typography/type-scale-tokens
        Text(text = stringResource(R.string.lives), style = MaterialTheme.typography.headlineMedium)
        Text(text = lives.toString(), style = MaterialTheme.typography.headlineMedium)
        Text(text = stringResource(R.string.score), style = MaterialTheme.typography.headlineMedium)
        Text(text = score.toString(), style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun DrawImageCat(catsViewModel: CatsViewModel){
    //Cuando se ontenga el gato activo se repintará la imagen
    val cat= catsViewModel.getActiveCat()
    if(cat?.image == null){
        Image(
            painter = painterResource(id = R.drawable.without_image),
            contentDescription = "Without image",
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        catsViewModel.get3RamdomCats()
    }else{
        catsViewModel.stateIsloading=true
        AsyncImage(
            model = cat.image.url,
            contentDescription = "Select a breed",
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        catsViewModel.stateIsloading=false
    }
}

@Composable
fun CatTest(catsViewModel: CatsViewModel, mediaPlayerClient: MediaPlayerClient){
    val context=LocalContext.current
    //val mediaPlayerClient by remember{ mutableStateOf(MediaPlayerClient(context))}
    DisposableEffect(Unit) {
        if(PreferenceManager.readPreferenceMusicOnOff(context)){
            mediaPlayerClient.playInGameMusic()
        }
        onDispose {
            mediaPlayerClient.stopInGameMusic()
        }
    }
    val correctAnswer=catsViewModel.state.correctAnswer
    val listCatRandom=catsViewModel.state.listRandomCats
    //Dibujamos el test
    for (i in 0..<listCatRandom.size) {
        Spacer(modifier = Modifier.size(10.dp))
        TextButton(
            modifier=Modifier.fillMaxWidth(),
            onClick = {
                catsViewModel.checkCorrectAnswer(i)
                if (correctAnswer == i) mediaPlayerClient.playSound(AudioEffectsType.typeSuccess)
                else mediaPlayerClient.playSound(AudioEffectsType.typeFail)
                catsViewModel.clickPressed=true
                catsViewModel.get3RamdomCats()
            }
        ){
            val text=(i+1).toString()+")  "+ catsViewModel.state.listRandomCats[i]?.name+"."
            if(catsViewModel.clickPressed){
                Text(
                    text = text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color=if(correctAnswer==i)Color.Black else Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier= Modifier
                        .fillMaxWidth()
                        .background(if (correctAnswer == i) Color.Green else Color.Red)
                )
            }else{
                Text(
                    text = text,
                    maxLines = 1,
                    //Esto añade 3 puntos al final para definir que hay más textp
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground ,
                    modifier=Modifier
                            .fillMaxWidth()
                )
            }
        }
    }//fin del for
}







