package es.tipolisto.breeds.ui.views.screens.cats

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCatScreen(navController: NavController, catsViewModel:CatsViewModel) {
    val context= LocalContext.current
    catsViewModel.get3RamdomCats()
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Playing cat breeds", color= Color.White, fontWeight = FontWeight.Bold)
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
    Log.d("TAG2", "pasa por game cat!")
    val cat=catsViewModel.getCatCorrectAnswer()
    var isSelectedRadionButton0 = false
    var isSelectedRadionButton1 = false
    var isSelectedRadionButton2 = false
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingsValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(cat==null || cat.image==null || cat.image.url==null){
            Image(
                painter = painterResource(id = R.drawable.without_image),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            catsViewModel.get3RamdomCats()
        }else{
            AsyncImage(
                model = cat.image.url,
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
                    catsViewModel.checkCorrectAnswer(0)
                }
            )
            val text=catsViewModel.state.stateListRandomCats.get(0)?.name
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
                   catsViewModel.checkCorrectAnswer(1)
               }
           )
           val text=catsViewModel.state.stateListRandomCats.get(1)?.name
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
                   catsViewModel.checkCorrectAnswer(2)
               }
           )
           val text=catsViewModel.state.stateListRandomCats.get(2)?.name
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
            Text(text = catsViewModel.stateLives.toString(), fontSize = 30.sp)
            Text(text = "  Score: ", fontSize = 30.sp)
            Text(text = catsViewModel.stateScore.toString(), fontSize = 30.sp)

            //if(catsViewModel.checkGameOver())

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



