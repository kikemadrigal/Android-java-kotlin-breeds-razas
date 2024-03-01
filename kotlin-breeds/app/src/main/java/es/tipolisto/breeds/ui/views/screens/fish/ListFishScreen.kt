@file:OptIn(ExperimentalMaterial3Api::class)

package es.tipolisto.breeds.ui.views.screens.fish

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.providers.FishProvider
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.FishViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFishScreen(navController:NavController,fishViewModel: FishViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Fish list", color= Color.White, fontWeight = FontWeight.Bold)
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
        LazyColumn(modifier=Modifier.padding(it)){

            if(FishProvider.listFish!!.isEmpty())
                Log.d("TAG","La lista de peces está vacía")
            else{
                val datos=FishProvider.listFish
                if(datos!=null){
                    items(datos){
                            item->
                        listIntemRow(item, navController)
                    }
                }
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListFishScreenPreview() {
    BreedsTheme {
        //ListFishScreen()
    }
}


@Composable
fun listIntemRow(fish: Fish, navController: NavController){
    Column(modifier= Modifier
        .padding(30.dp)
        .clickable {
            navController.navigate(AppScreens.DetailFishScreen.route+"/${fish.id}")
        }
        .background(MaterialTheme.colorScheme.primary), horizontalAlignment = Alignment.CenterHorizontally){
            getImageFromInternet(fish = fish, navController = navController)
            if(fish.img_src_set=="Not image")
                Text(text = ""+fish.img_src_set, color= Color.White, fontSize = 20.sp )
            Text(text = fish.name, color= Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp )
            Text(text = fish.urlWiki, color= Color.White, fontSize = 20.sp )
            Text(text = fish.meta.toString(), color= Color.White, fontSize = 16.sp )
    }
}

@Composable
fun getImageFromInternet(fish: Fish, navController: NavController){

    Column {
        //Image(painter= rememberAsyncImagePainter(request="https://loremflickr.com/100/100"), contentDescription = null )
        //Log.d("TAG", "imagen: "+fish.img_src_set)
        var url=fish.img_src_set
        if(url!="Not image" && url!=null){
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
                contentDescription = "Select a specie",
                modifier = Modifier
                    .size(400.dp,300.dp)
                    .padding(top = 10.dp),
                contentScale = ContentScale.Fit
            )
        }

    }

}