package es.tipolisto.breeds.ui.views.screens




import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.utils.AudioEffectsType
import es.tipolisto.breeds.utils.MediaPlayerClient


data class BottomMenu(
    val title: String,
    val image: Int,
    val navigation:String,
)

data class BottomNavigationItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNews:Boolean,
    //Queremos tener un recuento de lotes que sea anulable
    val badgeCount: Int?=null,
    val destination: String=AppScreens.MenuScreen.route
)

val itemsBottomMenu = listOf(
    BottomMenu(
        title="Cat",
        image=R.drawable.cat_menu,
        navigation = AppScreens.GameCatScreen.route
    ),
    BottomMenu(
        title="Dog",
        image=R.drawable.dog_menu,
        navigation = AppScreens.GameDogScreen.route
    ),
    BottomMenu(
        title="Fish",
        image=R.drawable.fish_menu,
        navigation = AppScreens.GameFishScreen.route
    )
)
val itemsBottomBar= listOf(
    BottomNavigationItem(
        title = "Beauties",
        selectedIcon = Icons.Filled.PhotoLibrary,
        unselectedIcon = Icons.Outlined.PhotoLibrary,
        hasNews = false,
        badgeCount = 0,
        destination = AppScreens.BeautiesScreen.route
    ),
    BottomNavigationItem(
        title = "Records",
        selectedIcon = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        hasNews = false,
        badgeCount = 0,
        destination = AppScreens.RecordsScreen.route
    ),
    BottomNavigationItem(
        title = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        hasNews = false,
        badgeCount = 0 ,
        destination = AppScreens.FavoritesScreen.route
    ),
    BottomNavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = false,
        badgeCount = 0,
        destination = AppScreens.SettingsScreen.route
    )
)


@Composable
fun MenuScreen(navController:NavController, mediaPlayerClient: MediaPlayerClient){
    val context= LocalContext.current
    //val mediaPlayerClient by remember{ mutableStateOf(MediaPlayerClient(context))}
    var isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
    var isEnabledMusic by remember {mutableStateOf(PreferenceManager.readPreferenceMusicOnOff(context))}
    DisposableEffect(Unit) {
        if(isEnabledMusic){
            Log.d("TAG","MenuScreen dice: en disposable efect vamos a reproducirla musica")
            mediaPlayerClient.playMenuMusic()
        }
        onDispose {
            Log.d("TAG","MenuScreen dice: en disposable efect vamos no reproducimos la musica")
            mediaPlayerClient.stopMenuMusic()
        }
    }


    BreedsTheme(darkTheme = isDarkMode) {
        Menu(navController, mediaPlayerClient)
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController:NavController, mediaPlayerClient: MediaPlayerClient) {
    val context =LocalContext.current
    //Los objetos de tipo state son los que observa jetpack compose
    Log.d("TAG","MenuScreen dice: el valor de mediaplayer.getMusica es "+PreferenceManager.readPreferenceMusicOnOff(context))


    //Con esto marcaremos el que está activo
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar {
                itemsBottomBar.forEachIndexed {index, item ->
                    NavigationBarItem(
                        label = { Text(text = item.title)},
                        selected = true,
                        onClick = {
                                    selectedItemIndex=index
                                    navController.navigate(item.destination)
                                  },
                        icon = {

                                Icon(
                                    imageVector=if (index==selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription=item.title
                                )
                        }
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier=Modifier.padding(it).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
             items(itemsBottomMenu){item ->
                 //Le ponemos un espacio entre celdas
                 Spacer(modifier = Modifier.size(20.dp))
                 TextButton(
                     modifier = Modifier
                         .width(200.dp),
                     onClick = {
                         mediaPlayerClient.playSound(AudioEffectsType.typeClick)
                         navController.popBackStack()
                         navController.navigate(item.navigation)
                     }
                 ) {
                     Image(
                         painter = painterResource(id = item.image),
                         contentDescription = "Breeds",
                         Modifier
                             .fillMaxSize(),
                         contentScale = ContentScale.FillWidth
                     )
                 }
             }
        }
    }//Final del scafold body
}//Final función



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MenuScreenPreview(){
    BreedsTheme(darkTheme = false) {
        //Menu()
    }
}


