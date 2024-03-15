package es.tipolisto.breeds.ui.navigation

sealed class AppScreens(val route:String){
    object SplashScreen:AppScreens("spashScreen")
    object MenuScreen:AppScreens("menuScreen")
    object RecordsScreen:AppScreens("recordsScreen")
    object SettingsScreen:AppScreens("settingsScreen")
    object FavoritesScreen:AppScreens("favoritesScreen")
    object BeautiesScreen:AppScreens("beautiesScreen")

    object LoginScreen:AppScreens("loginScreen")
    /*
     * Cats
     */
    object GameCatScreen:AppScreens("gameCatScreen")
    object ListCatsScreen:AppScreens("listCatsScreen")
    object DetailCatScreen:AppScreens("detailCatScreen")

    /*
     * Dogs
     */
    object GameDogScreen:AppScreens("gameDogScreen")
    object ListDogsScreen:AppScreens("listDogsScreen")
    object DetailDogScreen:AppScreens("detailDogScreen")

    /*
     * Fish
     */
    object GameFishScreen:AppScreens("gameFishScreen")
    object ListFishScreen:AppScreens("listFishScreen")
    object DetailFishScreen:AppScreens("detailFishScreen")
}