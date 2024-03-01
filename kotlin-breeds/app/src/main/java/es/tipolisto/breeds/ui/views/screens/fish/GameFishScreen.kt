package es.tipolisto.breeds.ui.views.screens.fish

import android.util.Log
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.FishViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameFishScreen(navController: NavController, fishViewModel: FishViewModel){
    val context= LocalContext.current
    fishViewModel.get3RamdomFish()
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Fish game", color= Color.White, fontWeight = FontWeight.Bold)
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
                        navController.navigate(AppScreens.ListFishScreen.route)
                    }){
                        Image(painter = painterResource(id = R.drawable.fish), contentDescription = "Fish list")
                    }
                }
            )
        }
    ) {
        GameFishScreenContent(it,fishViewModel, navController)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameFishScreenPreview() {
    BreedsTheme {
        //GameFishScreen()
    }
}



@Composable
fun GameFishScreenContent(paddingsValues:PaddingValues, fishViewModel: FishViewModel, navController: NavController){
    Log.d("TAG2", "pasa por game fish!")
    val fish=fishViewModel.getFishCorrectAnswer()
    var isSelectedRadionButton0 = false
    var isSelectedRadionButton1 = false
    var isSelectedRadionButton2 = false
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingsValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(fish==null || fish.id==null || fish.img_src_set=="Not image"){
            Image(
                painter = painterResource(id = R.drawable.without_image),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            fishViewModel.get3RamdomFish()
        }else{
            AsyncImage(
                model = fish.img_src_set,
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
                    fishViewModel.checkCorrectAnswer(0)
                }
            )
            val text=fishViewModel.state.stateListRandomFish.get(0)?.name
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
                    fishViewModel.checkCorrectAnswer(1)
                }
            )
            val text=fishViewModel.state.stateListRandomFish.get(1)?.name
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
                    fishViewModel.checkCorrectAnswer(2)
                }
            )
            val text=fishViewModel.state.stateListRandomFish.get(2)?.name
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
            Text(text = fishViewModel.stateLives.toString(), fontSize = 30.sp)
            Text(text = "  Score: ", fontSize = 30.sp)
            Text(text = fishViewModel.stateScore.toString(), fontSize = 30.sp)
        }

    }
}