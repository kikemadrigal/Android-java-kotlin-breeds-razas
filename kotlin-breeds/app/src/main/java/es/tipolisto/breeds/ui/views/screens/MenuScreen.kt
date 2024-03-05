package es.tipolisto.breeds.ui.views.screens




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
        image=R.drawable.cat,
        navigation = AppScreens.GameCatScreen.route
    ),
    BottomMenu(
        title="Dog",
        image=R.drawable.dog,
        navigation = AppScreens.GameDogScreen.route
    ),
    BottomMenu(
        title="Fish",
        image=R.drawable.fish,
        navigation = AppScreens.GameFishScreen.route
    )
)
val itemsBottomBar= listOf(
    BottomNavigationItem(
        title = "You",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Outlined.Face,
        hasNews = false,
        badgeCount = 0,
        destination = AppScreens.MenuScreen.route
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
fun MenuScreen(navController:NavController){
    Menu(navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController:NavController) {
    val context =LocalContext.current
    val scrollState = rememberScrollState()

    val mediaPLayerClient:MediaPlayerClient = remember{MediaPlayerClient(context).getInstance()}

    DisposableEffect(Unit) {
        if(PreferenceManager.readPreferenceMusicOnOff(context))
            mediaPLayerClient.playMenuMusic()
        onDispose {
                mediaPLayerClient.stopMenuMusic()
        }
    }
    //Con esto marcaremos el que está activo
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                itemsBottomBar.forEachIndexed {index, item ->
                    NavigationBarItem(
                        label = { Text(text = item.title)},
                        /*selected = selectedItemIndex==index,*/
                        selected = true,
                        onClick = {
                            selectedItemIndex=index
                            navController.navigate(item.destination)
                                  },
                        icon = {
                            /*BadgedBox(
                                badge = {
                                   if(item.badgeCount!=null){
                                       Badge {
                                           Text(text = item.badgeCount.toString())
                                       }
                                   }else if (item.hasNews){
                                        Badge()
                                   }
                                }
                            ) {*/
                                Icon(
                                    imageVector=if (index==selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription=item.title
                                )
                            //}
                        }
                    )
                }
            }
        }
    ) {


       Box(
           modifier = Modifier
               .fillMaxHeight()
               .padding(it)
               .verticalScroll(scrollState),
               //.background(Color.Cyan),

               //.paint(painterResource(id = R.drawable.splash_screen)),
           contentAlignment = Alignment.Center,
       ){
           Column (
               verticalArrangement = Arrangement.spacedBy(20.dp)
           ){
                itemsBottomMenu.forEachIndexed {idexed, item ->
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            mediaPLayerClient.playSound(AudioEffectsType.typeClick,context)
                            navController.navigate(item.navigation)
                        }
                    ) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = "Cats",
                            Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }
                }//Final del foreach
           }//Final del column
       }//Final del box
    }//Final del scafold body
}//Final función



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MenuScreenPreview(){
    BreedsTheme {
        //Menu()
    }
}


