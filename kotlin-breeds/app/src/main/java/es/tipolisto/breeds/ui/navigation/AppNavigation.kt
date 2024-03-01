package es.tipolisto.breeds.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.ui.viewModels.FishViewModel
import es.tipolisto.breeds.ui.views.screens.cats.DetailCatScreen
import es.tipolisto.breeds.ui.views.screens.cats.GameCatScreen
import es.tipolisto.breeds.ui.views.screens.cats.ListCatsScreen
import es.tipolisto.breeds.ui.views.screens.MenuScreen
import es.tipolisto.breeds.ui.views.screens.RecordsScreen
import es.tipolisto.breeds.ui.views.screens.SplashScreen
import es.tipolisto.breeds.ui.views.screens.dogs.DetailDogScreen
import es.tipolisto.breeds.ui.views.screens.dogs.GameDogScreen
import es.tipolisto.breeds.ui.views.screens.dogs.ListDogScreen
import es.tipolisto.breeds.ui.views.screens.favorites.FavoritesScreen
import es.tipolisto.breeds.ui.views.screens.fish.DetailFishScreen
import es.tipolisto.breeds.ui.views.screens.fish.GameFishScreen
import es.tipolisto.breeds.ui.views.screens.fish.ListFishScreen

@Composable
fun AppNavigation(catsViewModel:CatsViewModel, dogsViewModel: DogsViewModel, fishViewModel: FishViewModel){
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
            MenuScreen(navController)
        }
        composable(AppScreens.RecordsScreen.route){
            RecordsScreen(navController)
        }

        /*
         * Favorites
         */
        composable(AppScreens.FavoritesScreen.route){
            FavoritesScreen(navController)
        }


        /*
         * CATS
         */
        composable(AppScreens.GameCatScreen.route){
            GameCatScreen(navController,catsViewModel)
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
            DetailCatScreen(navController,catsViewModel,referenceImageId)
        }



        /*
         * DOGS
         */
        composable(AppScreens.GameDogScreen.route){
            GameDogScreen(navController,dogsViewModel)
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
            DetailDogScreen(navController, dogsViewModel, referenceImageId)
        }




        /**
         * Fish
         */
        composable(AppScreens.GameFishScreen.route){
            GameFishScreen(navController,fishViewModel)
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
                DetailFishScreen(navController,fishViewModel,id)
        }

    }
}