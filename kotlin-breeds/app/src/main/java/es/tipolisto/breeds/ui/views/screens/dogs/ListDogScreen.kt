@file:OptIn(ExperimentalMaterial3Api::class)

package es.tipolisto.breeds.ui.views.screens.dogs

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.data.providers.DogProvider
import es.tipolisto.breeds.data.providers.FishProvider
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.ui.viewModels.FishViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDogScreen(navController:NavController, dogsViewModel: DogsViewModel) {
    val context= LocalContext.current
    val isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(R.string.dog_list))
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back" )
                        }
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val datos=dogsViewModel.getAll()
                items(datos) { item ->
                    ListIntemRow(item, navController)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListFishScreenPreview() {
    BreedsTheme (darkTheme = false){
        //ListFishScreen()
    }
}


@Composable
fun ListIntemRow(dog: Dog, navController: NavController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                navController.navigate(AppScreens.DetailDogScreen.route + "/${dog.reference_image_id}")
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ){
        Column(
            modifier=Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DogImage(dog)
            Text(text = dog.name, style = MaterialTheme.typography.headlineLarge)
            Text(text = getDogContent(dog), style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun DogImage(dog: Dog){
    val url=dog.imageDog?.url
    if(url!="Not image"){
        val model by remember { mutableStateOf(url) }
        AsyncImage(
            model = model,
            contentDescription = "Select a specie",
            modifier = Modifier
                .size(300.dp, 200.dp)
                .padding(10.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun getDogContent(dog:Dog):String{
    return stringResource(id = R.string.dog_bred_for)+
            ": "+ dog.bred_for + "\n"+
            stringResource(id = R.string.dog_breed_group)+
            ": "+dog.breed_group

}