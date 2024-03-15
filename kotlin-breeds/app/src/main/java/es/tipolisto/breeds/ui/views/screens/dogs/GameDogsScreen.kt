package es.tipolisto.breeds.ui.views.screens.dogs

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.components.MyCircularProgressIndicator
import es.tipolisto.breeds.ui.components.onBackPressed
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.utils.AudioEffectsType
import es.tipolisto.breeds.utils.MediaPlayerClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDogScreen(navController: NavController, dogsViewModel: DogsViewModel, mediaPlayerClient: MediaPlayerClient) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        if (!dogsViewModel.justOnce) {
            dogsViewModel.loadAndInsertBuffer()
            dogsViewModel.get3RamdomDogs()
            dogsViewModel.justOnce = true
        }
    }
    //Conrol del botón atrás
    BackHandler {
        dogsViewModel.initGame()
        onBackPressed(navController, context)
    }

    val isDarkMode by remember { mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context)) }
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(R.string.playing_dog_breeds))
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                dogsViewModel.initGame()
                                onBackPressed(navController,context)
                            }
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back")
                        }

                    },
                    actions = {
                        IconButton(onClick = {
                            if(dogsViewModel.getAll().isEmpty()){
                                Toast.makeText(context,"Empty list",Toast.LENGTH_LONG).show()
                            }else{
                                navController.navigate(AppScreens.ListDogsScreen.route)
                            }

                        }) {
                            Image(painter = painterResource(id = R.drawable.dog),contentDescription = "Cat list")
                        }
                    }
                )
            }
        ) {
            GameDogScreenContent(it, dogsViewModel, navController, mediaPlayerClient)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameDogScreen() {
    BreedsTheme (darkTheme = false) {
        //GameDogScreen()
    }
}

@Composable
fun GameDogScreenContent(paddingsValues:PaddingValues, dogsViewModel:DogsViewModel,navController: NavController, mediaPlayerClient: MediaPlayerClient){
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingsValues),
            //.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(dogsViewModel.stateIsLoading){
            MyCircularProgressIndicator(isDisplayed = true)
        }else
            MyCircularProgressIndicator(isDisplayed = false)
        if(dogsViewModel.gameOver) {
            dogsViewModel.initGame()
            navController.popBackStack()
            navController.navigate(AppScreens.MenuScreen.route)
        }else{
            DogHud(dogsViewModel)
            DrawImageDog(dogsViewModel)
            DogTests(dogsViewModel, mediaPlayerClient)
        }
    }
}



@Composable
fun DogHud(dogsViewModel: DogsViewModel){
    val lives=dogsViewModel.state.lives.toString()
    val score=dogsViewModel.state.score.toString()
    Row (
        modifier= Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.secondary
            ),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = stringResource(R.string.lives), fontSize = 30.sp)
        Text(text = lives , fontSize = 30.sp)
        Text(text = stringResource(R.string.score), fontSize = 30.sp)
        Text(text = score, fontSize = 30.sp)
    }
}

@Composable
fun DrawImageDog(dogsViewModel: DogsViewModel){
    val dog=dogsViewModel.getActiveDog()
    if(dog==null || dog.imageDog?.url==null ){
        Image(
            painter = painterResource(id = R.drawable.without_image),
            contentDescription = "Splash breeds",
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        dogsViewModel.get3RamdomDogs()
    }else{
        //dogsViewModel.stateIsLoading=true
        AsyncImage(
            model = dog.imageDog?.url,
            contentDescription = "Select a breed",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 20.dp),
            contentScale = ContentScale.Fit
        )
        //dogsViewModel.stateIsLoading=false
    }
}

@Composable
fun DogTests(dogsViewModel: DogsViewModel, mediaPlayerClient:MediaPlayerClient){
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
    val correctAnswer=dogsViewModel.state.correctAnswer
    val listCatRandom=dogsViewModel.state.listRandomDogs
    for (i in 0..<listCatRandom.size) {
        Spacer(modifier = Modifier.size(10.dp))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                dogsViewModel.checkCorrectAnswer(i)

                if (correctAnswer == i) mediaPlayerClient.playSound(AudioEffectsType.typeSuccess)
                else mediaPlayerClient.playSound(AudioEffectsType.typeFail)
                dogsViewModel.clickPressed=true
                dogsViewModel.get3RamdomDogs()
            }
        ) {
            val text = (i+1).toString()+")  "+dogsViewModel.state.listRandomDogs.get(i)?.name
            if(dogsViewModel.clickPressed){
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
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground ,
                    modifier=Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}


