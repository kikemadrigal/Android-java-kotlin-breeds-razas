package es.tipolisto.breeds.ui.navigation

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.ui.viewModels.FavoritesViewModel
import es.tipolisto.breeds.ui.viewModels.FishViewModel
import es.tipolisto.breeds.ui.viewModels.LoginViewModel
import es.tipolisto.breeds.ui.viewModels.RecordsViewModel
import es.tipolisto.breeds.ui.views.screens.BeautiesScreen
import es.tipolisto.breeds.ui.views.screens.cats.DetailCatScreen
import es.tipolisto.breeds.ui.views.screens.cats.GameCatScreen
import es.tipolisto.breeds.ui.views.screens.cats.ListCatsScreen
import es.tipolisto.breeds.ui.views.screens.MenuScreen
import es.tipolisto.breeds.ui.views.screens.RecordsScreen
import es.tipolisto.breeds.ui.views.screens.SettingsScreen
import es.tipolisto.breeds.ui.views.screens.SplashScreen
import es.tipolisto.breeds.ui.views.screens.dogs.DetailDogScreen
import es.tipolisto.breeds.ui.views.screens.dogs.GameDogScreen
import es.tipolisto.breeds.ui.views.screens.dogs.ListDogScreen
import es.tipolisto.breeds.ui.views.screens.favorites.FavoritesScreen
import es.tipolisto.breeds.ui.views.screens.fish.DetailFishScreen
import es.tipolisto.breeds.ui.views.screens.fish.GameFishScreen
import es.tipolisto.breeds.ui.views.screens.fish.ListFishScreen
import es.tipolisto.breeds.ui.views.screens.login.LoginScreen
import es.tipolisto.breeds.utils.MediaPlayerClient

@Composable
fun AppNavigation(
    catsViewModel:CatsViewModel,
    dogsViewModel: DogsViewModel,
    fishViewModel: FishViewModel,
    favoritesViewModel: FavoritesViewModel,
    recordsViewModel:RecordsViewModel,
    loginViewModel: LoginViewModel,
    mediaPlayerClient:MediaPlayerClient
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {

        //Aquí definimos las navegaciones
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.MenuScreen.route){
            MenuScreen(navController,mediaPlayerClient)
        }
        composable(AppScreens.RecordsScreen.route){
            RecordsScreen(recordsViewModel,navController)
        }
        composable(AppScreens.SettingsScreen.route){
            SettingsScreen(navController,mediaPlayerClient)
        }
        composable(AppScreens.BeautiesScreen.route){
            BeautiesScreen(navController)
        }
        composable(AppScreens.LoginScreen.route){
            LoginScreen(loginViewModel,navController)
        }

        /*
         * Favorites
         */
        composable(AppScreens.FavoritesScreen.route){
            FavoritesScreen(navController, favoritesViewModel)
        }


        /*
         * CATS
         */
        composable(AppScreens.GameCatScreen.route){
            GameCatScreen(navController,catsViewModel,recordsViewModel, mediaPlayerClient)
        }
        composable(AppScreens.ListCatsScreen.route){
            ListCatsScreen(navController,catsViewModel)
        }
        composable(
            route=AppScreens.DetailCatScreen.route+"/{reference_image_id}",
            arguments = listOf(
                navArgument(name="reference_image_id"){type= NavType.StringType})
        )
        {
            val referenceImageId:String?=it.arguments?.getString("reference_image_id")
            requireNotNull(referenceImageId, { "No puede ser nulo" })
            DetailCatScreen(navController,catsViewModel,referenceImageId,favoritesViewModel)
        }



        /*
         * DOGS
         */
        composable(AppScreens.GameDogScreen.route){
            GameDogScreen(navController,dogsViewModel, mediaPlayerClient)
        }
        composable(AppScreens.ListDogsScreen.route){
            ListDogScreen(navController,dogsViewModel)
        }
        composable(
            route=AppScreens.DetailDogScreen.route+"/{reference_image_id}",
            arguments = listOf(
                navArgument(name="reference_image_id"){type= NavType.StringType})
        )
        {
            val referenceImageId:String?=it.arguments?.getString("reference_image_id")
            requireNotNull(referenceImageId, { "No puede ser nulo" })
            DetailDogScreen(navController, dogsViewModel, referenceImageId,favoritesViewModel)
        }




        /**
         * Fish
         */
        composable(AppScreens.GameFishScreen.route){
           GameFishScreen(navController,fishViewModel, mediaPlayerClient)
        }
        composable(AppScreens.ListFishScreen.route){
            ListFishScreen(navController, fishViewModel)
        }
        composable(
            route=AppScreens.DetailFishScreen.route+"/{id}",
            arguments = listOf(
                navArgument(name="id"){type= NavType.IntType})
        ){
                val id:Int?=it.arguments?.getInt("id")
                requireNotNull(id, { "No puede ser nulo" })
                DetailFishScreen(navController,fishViewModel,id,favoritesViewModel)
        }

    }
}