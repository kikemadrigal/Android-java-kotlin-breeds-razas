package es.tipolisto.breeds.ui.views.screens.dogs

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.components.MyToast
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.ui.viewModels.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDogScreen(
    navController: NavController,
    dogsViewModel: DogsViewModel,
    reference_image_id:String,
    favoritesViewModel: FavoritesViewModel
){
    val dog = dogsViewModel.getDogByReferenceImageId(reference_image_id)
    favoritesViewModel.checkIsInFavorites(dog?.id.toString())
    val isFavorite: Boolean by favoritesViewModel.isFavorite.observeAsState(initial = false)
    val context= LocalContext.current
    val isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(R.string.dog_detail), color = Color.White, fontWeight = FontWeight.Bold)
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon( imageVector = Icons.Default.ArrowBack,contentDescription = "Back" )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                           if (dog != null) {
                               if (isFavorite){
                                   val list=favoritesViewModel.getFavoritesByIdAnimal(dog.id.toString())
                                   if(list!=null)
                                       favoritesViewModel.delete(list[0])
                                   favoritesViewModel.setFavorite(false)
                               //Si no está en la lista de favoritos
                               }else{
                                   favoritesViewModel.createFavorite(dog)
                                   Toast.makeText(context, "Added cat to favorites", Toast.LENGTH_LONG)
                                       .show()
                                   favoritesViewModel.setFavorite(true)
                               }
                           }
                        }) {
                            if(!isFavorite)
                                Image(  painter = painterResource(id = R.drawable.favorites_enabled), contentDescription = "")
                        }
                        IconButton(onClick = {
                            navController.navigate(AppScreens.FavoritesScreen.route)
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.favorite_list),
                                contentDescription = "Dog list"
                            )
                        }
                    }
                )
            }
        ) {
            val dog = dogsViewModel.getDogByReferenceImageId(reference_image_id)
            val url=dog?.imageDog?.url
            var model by remember { mutableStateOf(url) }
            Card(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier=Modifier
                        .padding(20.dp),
                ) {
                    AsyncImage(
                        model = model,
                        contentDescription = "Image dog",
                        modifier = Modifier
                            .size(300.dp, 200.dp),
                        contentScale = ContentScale.Fit
                    )
                    if (dog != null) {
                        Text(
                            text = dog.name,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = getDetailDog(dog) + "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }//Final del column
            }//Final del card
        }//Final del scafold
    }//Final del theme
}
@Composable
fun DetailDogScreenContent(it:PaddingValues, dog: Dog){
    Column(modifier = Modifier.padding(20.dp)){

    }
}

@Composable
private fun getDetailDog(dog: Dog):String{
    var content: String =  dog.bred_for+ "\n"
    content +=
        stringResource(id = R.string.dog_bred_for)+": "+ dog.bred_for + "\n"+
        stringResource(id = R.string.dog_breed_group)+": "+dog.breed_group+ "\n"+
        stringResource(id = R.string.dog_life_span)+": "+dog.life_span + "\n" +
        stringResource(id = R.string.dog_temperament)+": "+dog.temperament + "\n" +
        stringResource(id = R.string.dog_origin)+": "+dog.origin + "\n"+
        stringResource(id = R.string.dog_weight)+": "+dog.weight + "\n"+
        stringResource(id = R.string.dog_height)+": "+dog.height + "\n"
    return content
}

