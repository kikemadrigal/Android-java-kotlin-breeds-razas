package es.tipolisto.breeds.ui.views.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme

@Composable
fun MenuScreen(navController:NavController){
    Menu(navController)
}
@Composable
fun Menu(navController:NavController) {
    val context=LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Menu",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Breeds developments TL",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            onClick = {
                navController.navigate(AppScreens.GameCatScreen.route)
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