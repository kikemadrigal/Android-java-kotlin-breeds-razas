package es.tipolisto.breeds.ui.views.screens.dogs

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.ui.viewModels.DogsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDogScreen(navController: NavController, dogsViewModel: DogsViewModel) {
    //dogsViewModel.get3RamdomDogs()
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Playing dog breeds", color= Color.White, fontWeight = FontWeight.Bold)
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
                        navController.navigate(AppScreens.ListDogsScreen.route)
                    }){
                        Image(painter = painterResource(id = R.drawable.dog), contentDescription = "Cat list")
                    }
                }
            )
        }
    ){
        GameDogScreenContent(it,dogsViewModel, navController)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameCatScreen() {
    BreedsTheme {
        //GameDogScreen()
    }
}

@Composable
fun GameDogScreenContent(paddingsValues:PaddingValues, dogsViewModel:DogsViewModel,navController: NavController){
    val dog=dogsViewModel.getDogCorrectAnswer()
    var isSelectedRadionButton0 = false
    var isSelectedRadionButton1 = false
    var isSelectedRadionButton2 = false
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingsValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(dog==null || dog.imageDog?.url==null ){
            Image(
                painter = painterResource(id = R.drawable.without_image),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            dogsViewModel.get3RamdomDogs()
        }else{
            AsyncImage(
                model = dog.imageDog?.url,
                contentDescription = "Select a breed",
                modifier = Modifier
                    .size(400.dp, 300.dp)
                    .padding(top = 20.dp),
                contentScale = ContentScale.Fit
            )
        }


        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = isSelectedRadionButton0,
                onClick = {
                    isSelectedRadionButton0=true
                    dogsViewModel.checkCorrectAnswer(0)
                }
            )
            val text=dogsViewModel.state.stateListRandomDogs.get(0)?.name
            Text(text = text+".")
        }
        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp),
           verticalAlignment = Alignment.CenterVertically
        ){
           RadioButton(
               selected = isSelectedRadionButton1,
               onClick = {
                   isSelectedRadionButton1=true
                   dogsViewModel.checkCorrectAnswer(1)
               }
           )
           val text=dogsViewModel.state.stateListRandomDogs.get(1)?.name
           Text(text = text+".")
        }
        Row (modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp),
           verticalAlignment = Alignment.CenterVertically
        ){
           RadioButton(
               selected = isSelectedRadionButton2,
               onClick = {
                   isSelectedRadionButton2=true
                   dogsViewModel.checkCorrectAnswer(2)
               }
           )
           val text=dogsViewModel.state.stateListRandomDogs.get(2)?.name
           Text(text = text+".")
        }
        Row (
            modifier= Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(100.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "Lives: ", fontSize = 30.sp)
            Text(text = dogsViewModel.stateLives.toString(), fontSize = 30.sp)
            Text(text = "  Score: ", fontSize = 30.sp)
            Text(text = dogsViewModel.stateScore.toString(), fontSize = 30.sp)
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



