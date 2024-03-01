package es.tipolisto.breeds.ui.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.repositories.CatRepository
import es.tipolisto.breeds.data.repositories.DogRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.ui.components.MyCircularProgressIndicator
import es.tipolisto.breeds.ui.navigation.AppScreens

@Composable
fun SplashScreen(navController:NavController){
    Splash()
    MyCircularProgressIndicator(isDisplayed = true)
    LaunchedEffect(key1 = true ){
        CatRepository.loadCatsAndInsertBuffer()
        DogRepository.loadDogAndInsertBuffer()
        FishRepository.loadFishAndInsertBuffer()
        //Antes de navegar limpiamos la pila de ventanas para que no vuelva a salir el splash
        navController.popBackStack()
        navController.navigate(AppScreens.MenuScreen.route)
    }
}
@Composable
fun Splash(){
    Column (modifier = Modifier.padding(0.dp)){
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "Splash breeds",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    Splash()
}



