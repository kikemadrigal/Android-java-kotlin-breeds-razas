package es.tipolisto.breeds.ui.views.screens.cats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.components.MyCircularProgressIndicator
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.utils.AudioEffectsType
import es.tipolisto.breeds.utils.MediaPlayerClient


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCatScreen(navController: NavController, catsViewModel:CatsViewModel) {
    val context= LocalContext.current
    val mediaPLayerClient:MediaPlayerClient by remember{ mutableStateOf(MediaPlayerClient(context))}

    DisposableEffect(Unit) {
        if(PreferenceManager.readPreferenceMusicOnOff(context)){
            mediaPLayerClient.playInGameMusic()
        }
        onDispose {
            mediaPLayerClient.stopInGameMusic()
        }
    }
    //val catsViewModelState= remember {catsViewModel}
    LaunchedEffect(key1 = true){
        if (!catsViewModel.justOnce) {
            catsViewModel.loadAndInsertBuffer()
            catsViewModel.justOnce=true
        }
    }
    catsViewModel.get3RamdomCats()
    var isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.cat_game_playing_cat_breeds), color= Color.White, fontWeight = FontWeight.Bold)
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon={
                        IconButton(onClick = { navController.popBackStack()}) {
                            Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back", tint = Color.White)
                        }

                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(AppScreens.ListCatsScreen.route)
                        }){
                            Image(painter = painterResource(id = R.drawable.cat), contentDescription = "Cat list")
                        }
                    }
                )
            }
        ){
            GameCatScreenContent(it,catsViewModel, navController, mediaPLayerClient)
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
fun GameCatScreenContent(paddingsValues:PaddingValues, catsViewModel:CatsViewModel,navController: NavController, mediaPlayerClient: MediaPlayerClient){
    val context=LocalContext.current
    val cat= catsViewModel.getCatCorrectAnswer()

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingsValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier= Modifier
                .fillMaxWidth()
                .border(
                    0.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Color.White
                ),
            horizontalArrangement = Arrangement.Center
        ){
        Text(text = "Lives: ", fontSize = 30.sp)
        Text(text = catsViewModel.stateLives.toString(), fontSize = 30.sp)
        Text(text = "  Score: ", fontSize = 30.sp)
        Text(text = catsViewModel.stateScore.toString(), fontSize = 30.sp)
        }

        if(cat?.image == null){
            Image(
                painter = painterResource(id = R.drawable.without_image),
                contentDescription = "Without image",
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            catsViewModel.get3RamdomCats()
            mediaPlayerClient.playSound(AudioEffectsType.typeClick, context)
        }else{
            AsyncImage(
                model = cat.image.url,
                contentDescription = "Select a breed",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        if(catsViewModel.isLoading){
            MyCircularProgressIndicator(isDisplayed = true, animal="cats")
        }else
            MyCircularProgressIndicator(isDisplayed = false,animal="cats")

        var color=Color.Transparent
        for (i in 0..2){
            if(catsViewModel.result){
                if (catsViewModel.state.correctAnswer==i)color=Color.Green
                else color=Color.Green
                MyAlertDialog(navController)
            }
            Row(modifier= Modifier
                .fillMaxWidth()
                .background(color)
                .padding(20.dp)
                .clickable {
                    val result = catsViewModel.checkCorrectAnswer(i)
                    if (result) mediaPlayerClient.playSound(
                        AudioEffectsType.typeSuccess, context
                    ) else mediaPlayerClient.playSound(AudioEffectsType.typeFail, context)
                },
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = (i+1).toString()+") ", fontSize = 24.sp)
                val text=catsViewModel.state.stateListRandomCats.get(i)?.name
                Text(text = text+".", fontSize = 24.sp)
            }
        }


    }
}

@Composable
private fun MyAlertDialog(navController: NavController){
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton( onClick = { /*TODO*/ }) {
                Text(text = stringResource(id=R.string.next))
                navController.popBackStack()
                navController.navigate(AppScreens.MenuScreen.route)
            }
        },
        title = {Text(text = stringResource(id=R.string.next))},
        text =  { Text(text = "Game over")}
    )
}








