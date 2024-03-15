package es.tipolisto.breeds.ui.views.screens.favorites


import android.widget.Toast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.FavoritesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, favoritesViewModel: FavoritesViewModel){
    val context= LocalContext.current
    var isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
    //var checked by remember {mutableStateOf(false)}
    BreedsTheme(darkTheme = isDarkMode) {

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(R.string.favorites))
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Column (modifier=Modifier.padding(it)){
                AnimalTab(favoritesViewModel)
            }

        }//Final de scafold
    }//Final de BreedsTheme


}


@Composable
fun getAllForType( favoritesViewModel:FavoritesViewModel, animal:String){
    val context= LocalContext.current
    var listFavorites= emptyList<FavoritesEntity>()
    when (animal){
        "All"->listFavorites= favoritesViewModel.getAll()
        "Cats"->listFavorites= favoritesViewModel.getAllCats()
        "Dogs"->listFavorites= favoritesViewModel.getAllDogs()
        "Fish"->listFavorites= favoritesViewModel.getAllFish()
    }
    //val listFavorites= favoritesViewModel.getAllFish()
    if (listFavorites.isEmpty())
        Text(text = stringResource(id = R.string.empty_list))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //En realidad esta función es así=items(listFavorites, onCLick(favoritesEntity){...código..})
        items(listFavorites) { item ->
            ListIntemRow(item) {
                favoritesViewModel.delete(it)
                Toast.makeText(context, "Deleting cat from favorites", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
@Composable
fun ListIntemRow(favoritesEntity: FavoritesEntity, onClick:(FavoritesEntity)->Unit){
    Column {
        val model:String=favoritesEntity.image
        //val model by remember { mutableStateOf(url) }
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
        //A esto se le llama propagación
        IconButton(onClick = { onClick(favoritesEntity) }
        ){
            Image(painter = painterResource(id = R.drawable.no_favorite), contentDescription = "Cat list")
        }
        AsyncImage(
            model = model,
            contentDescription = "Select a breed",
            modifier = Modifier
                .size(400.dp, 300.dp)
                .padding(top = 10.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = favoritesEntity.nameBreed, style = MaterialTheme.typography.headlineLarge )
        Text(text = favoritesEntity.description, style = MaterialTheme.typography.bodyLarge )
    }
}

@Composable
fun AnimalTab(favoritesViewModel: FavoritesViewModel){
    var tabIndex by remember { mutableStateOf(0) }
    val lista= listOf(stringResource(R.string.all),stringResource(R.string.cats), stringResource(R.string.dogs), stringResource(R.string.fish))
    Column (modifier= Modifier.fillMaxWidth()){
        TabRow(selectedTabIndex = tabIndex) {
            lista.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> getAllForType(favoritesViewModel,"All")
            1 -> getAllForType(favoritesViewModel,"Cats")
            2 -> getAllForType(favoritesViewModel,"Dogs")
            3 -> getAllForType(favoritesViewModel, "Fish")
        }
    }
}

/**
when (favoritesEntity.animal){
"Cat" -> {
//val cat:Cat?=CatRepository.getCatFromIdFromBuffer(favoritesEntity.idBreed)
getFavorite(favoritesEntity,favoritesViewModel)
}
"Dog" -> {
getFavoriteDog(favoritesEntity,favoritesViewModel)
}
"Fish" -> {
//Log.d("TAG", "FavoritesScreen dice: fish pasa por el when")
val fish:Fish?= FishRepository.getFishFromSpecieIdInBuffer(favoritesEntity.id!!)
if(fish!=null)
getFavoriteFish(fish,favoritesEntity,favoritesViewModel)
}
 */


