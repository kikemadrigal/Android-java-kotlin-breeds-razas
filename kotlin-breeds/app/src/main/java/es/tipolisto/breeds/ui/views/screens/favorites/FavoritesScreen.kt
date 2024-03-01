package es.tipolisto.breeds.ui.views.screens.favorites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.repositories.CatRepository
import es.tipolisto.breeds.data.repositories.DogRepository
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.ui.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Favorites", color= Color.White, fontWeight = FontWeight.Bold)
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
    ) {
        val context= LocalContext.current
        LazyColumn(modifier= Modifier.padding(it)){
            Log.d("TAG","FavoritesScreen dice: mostrando favoritos")
            val listFavorites=FavoritesRepository.getAll(context)
            Log.d("TAG","FavoritesScreen dice: enoontrados: "+listFavorites.size+" favorites")
            if(listFavorites.isEmpty())
                Log.d("TAG","FavoritesScreen dice: No hay records")
            else{
                items(listFavorites){
                        item->
                    listIntemRow(item, navController)
                    /*Column() {
                        Text(text = item.toString())
                        Spacer(modifier = Modifier.size(5.dp))
                    }*/
                }
            }

        }
    }
}


@Composable
fun listIntemRow(favoritesEntity: FavoritesEntity, navController: NavController){
    val favorites=
    Row(modifier= Modifier
        .padding(10.dp, 5.dp, 10.dp, 5.dp)
        .background(MaterialTheme.colorScheme.primary), verticalAlignment = Alignment.CenterVertically){
        when (favoritesEntity.animal){
            "Cat" -> {
                val cat:Cat?=CatRepository.getCatFromIdFromBuffer(favoritesEntity.idBreed)
                if(cat!=null)
                    getFavoriteCat(cat,navController)
            }
            "Dog" -> {
                val dog: Dog?= DogRepository.getDogFromIdInBuffer(favoritesEntity.idBreed)
                if(dog!=null)
                    getFavoriteDog(dog,navController)
            }
            "Fish" -> {
                val cat:Cat?=CatRepository.getCatFromIdFromBuffer(favoritesEntity.idBreed)
                if(cat!=null)
                    getFavoriteCat(cat,navController)
            }
        }

    }
}


@Composable
fun getFavoriteCat(cat:Cat, navController: NavController){
    Column {
        var url=cat.image?.url
        var model by remember { mutableStateOf(url) }
        val context= LocalContext.current
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
        AsyncImage(
            model = model,
            contentDescription = "Select a breed",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 10.dp)
                .clickable {
                    navController.navigate(AppScreens.DetailCatScreen.route + "/${cat.reference_image_id}")
                },
            contentScale = ContentScale.Crop
        )
        Text(text = cat.name, color= Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp )
        Text(text = cat.description, color= Color.White, fontSize = 20.sp )
    }
}

@Composable
fun getFavoriteDog(dog:Dog, navController: NavController){
    Column {
        var url=dog.imageDog?.url
        var model by remember { mutableStateOf(url) }
        val context= LocalContext.current
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
        AsyncImage(
            model = model,
            contentDescription = "Select a breed",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 10.dp)
                .clickable {
                    navController.navigate(AppScreens.DetailDogScreen.route + "/${dog.reference_image_id}")
                },
            contentScale = ContentScale.Crop
        )
        Text(text = dog.name, color= Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp )
        Text(text = dog.bred_for, color= Color.White, fontSize = 20.sp )
    }
}


@Composable
fun getFavoriteFish(fish: Fish, navController: NavController){
    Column {
        var url=fish.img_src_set
        var model by remember { mutableStateOf(url) }
        val context= LocalContext.current
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
        AsyncImage(
            model = model,
            contentDescription = "Select a breed",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 10.dp)
                .clickable {
                    navController.navigate(AppScreens.DetailFishScreen.route + "/${fish.id}")
                },
            contentScale = ContentScale.Crop
        )
        Text(text = fish.name, color= Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp )
        Text(text = fish.urlWiki, color= Color.White, fontSize = 20.sp )
    }
}