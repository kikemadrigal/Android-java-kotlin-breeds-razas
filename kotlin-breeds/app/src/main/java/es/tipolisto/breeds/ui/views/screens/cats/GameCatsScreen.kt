package es.tipolisto.breeds.ui.views.screens.cats

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.remember
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
    val mediaPLayerClient:MediaPlayerClient= remember{MediaPlayerClient(context).getInstance()}
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
        GameCatScreenContent(it,catsViewModel, navController)
    }
    //
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameCatScreen() {
    BreedsTheme {
        //GameCatScreen()
    }
}

@Composable
fun GameCatScreenContent(paddingsValues:PaddingValues, catsViewModel:CatsViewModel,navController: NavController){
    val context=LocalContext.current
    val mediaPLayerClient:MediaPlayerClient= remember{MediaPlayerClient(context).getInstance()}
    val cat=catsViewModel.getCatCorrectAnswer()
    /*var isSelectedRadionButton0 = false
    var isSelectedRadionButton1 = false
    var isSelectedRadionButton2 = false*/

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
                    //.size(400.dp,300.dp)
                    .height(300.dp)
            )
            catsViewModel.get3RamdomCats()
            mediaPLayerClient.playSound(AudioEffectsType.typeClick, context)
        }else{
            AsyncImage(
                model = cat.image.url,
                contentDescription = "Select a breed",
                modifier = Modifier
                    //.size(400.dp,300.dp)
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }


        if(catsViewModel.isLoading){
            MyCircularProgressIndicator(isDisplayed = true, animal="cats")
        }else
            MyCircularProgressIndicator(isDisplayed = false,animal="cats")



        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                //isSelectedRadionButton0=true
                val result = catsViewModel.checkCorrectAnswer(0)
                if (result) mediaPLayerClient.playSound(AudioEffectsType.typeSuccess,context
                ) else mediaPLayerClient.playSound(AudioEffectsType.typeFail, context)
            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "A) ", fontSize = 24.sp)
            val text=catsViewModel.state.stateListRandomCats.get(0)?.name
            Text(text = text+".", fontSize = 24.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable { //isSelectedRadionButton1=true
                    val result = catsViewModel.checkCorrectAnswer(1)
                    if (result) mediaPLayerClient.playSound(AudioEffectsType.typeSuccess,context
                    ) else mediaPLayerClient.playSound( AudioEffectsType.typeFail,context)
                },
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(text = "B) ", fontSize = 24.sp)
            val text=catsViewModel.state.stateListRandomCats.get(1)?.name
            Text(text = text+".", fontSize = 24.sp)
        }
        Row (modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { //isSelectedRadionButton2=true
                val result = catsViewModel.checkCorrectAnswer(2)
                if (result) mediaPLayerClient.playSound(
                    AudioEffectsType.typeSuccess,
                    context
                ) else mediaPLayerClient.playSound(AudioEffectsType.typeFail, context)
            },
           verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "C) ", fontSize = 24.sp)
            Text(
                text = catsViewModel.state.stateListRandomCats.get(2)?.name + ".",
                fontSize = 24.sp
            )
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








