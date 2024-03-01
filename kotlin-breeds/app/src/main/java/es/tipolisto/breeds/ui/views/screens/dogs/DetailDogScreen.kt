package es.tipolisto.breeds.ui.views.screens.dogs

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.ui.viewModels.FishViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDogScreen(navController: NavController, dogsViewModel: DogsViewModel, reference_image_id:String){
    val context= LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Dog detail", color= Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon={
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val dog = dogsViewModel.getDogByReferenceImageId(reference_image_id)
                        if(dog!=null){
                            dogsViewModel.createFavorite(context,dog.id)
                            Toast.makeText(context, "Added dog to favorites", Toast.LENGTH_LONG).show()
                        }
                    }){
                        Image(painter = painterResource(id = R.drawable.favorites_disabled), contentDescription = "Dog favorites")
                    }
                    IconButton(onClick = {
                        navController.navigate(AppScreens.FavoritesScreen.route)
                    }){
                        Image(painter = painterResource(id = R.drawable.favorite_list), contentDescription = "Dog list")
                    }
                }
            )
        }
    ) {
        if(reference_image_id==null) Log.d("TAG", "DetailDogScreen dice:  el referenceImageId es nulo")
        else Log.d("TAG", "DetailDogScreen dice: recibido el especia de pez_id: "+reference_image_id)
        val dog = dogsViewModel.getDogByReferenceImageId(reference_image_id)
        Column(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.primary)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(dog!=null)
                DetailDogScreenContent(it, dog=dog)
            else{
                Log.d("TAG", "No hay pez para mostrar el detalle")
            }
        }
    }
}
@Composable
fun DetailDogScreenContent(it:PaddingValues, dog: Dog){
    Column(modifier = Modifier.padding(20.dp)){
        AsyncImage(
            model = dog.imageDog?.url,
            contentDescription = "Breed dog",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 20.dp),
            contentScale = ContentScale.Fit
        )
        if(dog!=null){
            Text(
                text = dog.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(text = getDetailDog(dog) + "", color = Color.White, fontSize = 20.sp)
        }
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

