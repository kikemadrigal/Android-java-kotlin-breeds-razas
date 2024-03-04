package es.tipolisto.breeds.ui.views.screens


import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.data.repositories.CatRepository
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.utils.MediaPlayerClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

@Composable
fun MenuScreen(navController:NavController){

    Menu(navController)
}
@Composable
fun Menu(navController:NavController) {
    val context=LocalContext.current
    val scrollState= rememberScrollState()

    val menuMusic: MediaPlayer = remember {MediaPlayer.create(context, R.raw.intro)}
    DisposableEffect(Unit) {
        menuMusic.isLooping = true
        if(PreferenceManager.readPreferenceMusicOnOff(context))
            menuMusic.start()
        onDispose {
            if(menuMusic.isPlaying)
                menuMusic.stop()
        }
    }


    Column (
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Menu",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Breeds developments TL",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            onClick = {
                navController.navigate(AppScreens.GameCatScreen.route)
                //if(mediaPlayer.isLooping)mediaPlayer.stop()
                //Log.d("TAG", "CatsViewModel dice en init: obtenida lista de gatos con tamaño "+ CatProvider.listCats.size)
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            onClick = {
                //Toast.makeText(context, "Vamos al juego de perros",Toast.LENGTH_LONG).show()
                navController.navigate(AppScreens.GameDogScreen.route)
                //if(mediaPlayer.isLooping)mediaPlayer.stop()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            onClick = {
                //Toast.makeText(context, "Vamos al juego de perros",Toast.LENGTH_LONG).show()
                navController.navigate(AppScreens.GameFishScreen.route)
                //if(mediaPlayer.isLooping)mediaPlayer.stop()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.fish),
                contentDescription = "Fish game start",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            onClick = {
                //Toast.makeText(context, "Vamos a ver las puntuaciones",Toast.LENGTH_LONG).show()
                navController.navigate(AppScreens.RecordsScreen.route)
                //if(mediaPlayer.isLooping)mediaPlayer.stop()
            }

        ) {
            Image(
                painter = painterResource(id = R.drawable.records),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            onClick = {
                //Toast.makeText(context, "Vamos a los settings",Toast.LENGTH_LONG).show()
                navController.navigate(AppScreens.SettingsScreen.route)
                //if(mediaPlayer.isLooping)mediaPlayer.stop()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Splash breeds",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MenuScreenPreview(){
    BreedsTheme {
        //Menu()
    }
}

