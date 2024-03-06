package es.tipolisto.breeds.ui.views.screens




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.utils.MediaPlayerClient



@Composable
fun BeautiesScreen(navController:NavController){
    val context= LocalContext.current
    var isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}

    BreedsTheme(darkTheme = isDarkMode) {
        Beauty(navController)
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Beauty(navController:NavController) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Beauty ranking", color= Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon={
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ){
           Column (
               modifier=Modifier.padding(it),
               verticalArrangement = Arrangement.spacedBy(20.dp)
           ){
               Text(text = "Sube la imagen de tu animal")
               Text(text = "Vota en el ranking de los animales más bellos")
           }//Final del column
    }//Final del scafold
}//Final de la funcion





