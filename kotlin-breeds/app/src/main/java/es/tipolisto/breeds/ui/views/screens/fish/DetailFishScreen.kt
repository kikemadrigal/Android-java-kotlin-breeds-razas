package es.tipolisto.breeds.ui.views.screens.fish

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.viewModels.FishViewModel
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFishScreen(navController: NavController, fishViewModel: FishViewModel, id:Int){
    val context= LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Fish detail", color= Color.White, fontWeight = FontWeight.Bold)
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
                        fishViewModel.createFavorite(context,id)
                        Toast.makeText(context, "Added fish to favorites", Toast.LENGTH_LONG).show()
                    }){
                        Image(painter = painterResource(id = R.drawable.favorites_disabled), contentDescription = "Cat list")
                    }
                    IconButton(onClick = {
                        navController.navigate(AppScreens.FavoritesScreen.route)
                    }){
                        Image(painter = painterResource(id = R.drawable.favorite_list), contentDescription = "Cat list")
                    }
                }
            )
        }
    ) {
        if(id==null) Log.d("TAG", " el referenceImageId es nulo")
        else Log.d("TAG", "DetailFishScreen dice: recibido el especia de pez_id: "+id)
        val fish = fishViewModel.getFishByIdFish(id)
        Column(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.primary)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(fish!=null)
                DetailFishScreenContent(it, fish=fish)
            else{
                Log.d("TAG", "No hay pez para mostrar el detalle")
            }
        }
    }
}
@Composable
fun DetailFishScreenContent(it:PaddingValues, fish: Fish){
    Column(modifier = Modifier.padding(20.dp)){

        AsyncImage(
            model = fish.img_src_set,
            contentDescription = "Specie fish",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 20.dp),
            contentScale = ContentScale.Fit
        )
        if(fish!=null){
            Text(text = fish.name, color = Color.White,fontWeight = FontWeight.Bold,fontSize = 24.sp)
            Text(text = fish.urlWiki + "", color = Color.White, fontSize = 20.sp)
            Text(text = fish.meta + "", color = Color.White, fontSize = 20.sp)
        }
    }
}



