package es.tipolisto.breeds.ui.views.screens.cats

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
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
import es.tipolisto.breeds.ui.components.RatingBar
import es.tipolisto.breeds.ui.navigation.AppScreens
import es.tipolisto.breeds.ui.viewModels.CatsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCatScreen(navController: NavController, catsViewModel: CatsViewModel, referenceImageId:String){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Cat detail", color= Color.White, fontWeight = FontWeight.Bold)
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
                        val cat = catsViewModel.getCatByImageCat(referenceImageId)
                        if (cat!=null){
                            catsViewModel.createFavorite(context,cat.id)
                            Toast.makeText(context, "Added cat to favorites", Toast.LENGTH_LONG).show()
                        }
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
        //if(referenceImageId==null) Log.d("TAG", "DetailCatScreen dice:  el referenceImageId es nulo")
        //else Log.d("TAG", "DetailCatScreen dice: recibido el breed_cat_id: "+referenceImageId)
        val cat = catsViewModel.getCatByImageCat(referenceImageId)
        if(cat==null) navController.navigate(AppScreens.ListCatsScreen.route)
        var url = cat?.image?.url
        var model by remember { mutableStateOf(url) }
        Column(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.primary)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.padding(20.dp)){
                AsyncImage(
                    model = model,
                    contentDescription = "Select a breed",
                    modifier = Modifier
                        .size(400.dp, 300.dp)
                        .padding(top = 20.dp)
                        .clickable {
                            Toast
                                .makeText(context, "Go to detail cat", Toast.LENGTH_LONG)
                                .show()
                        },
                    contentScale = ContentScale.Fit
                )
                if(cat!=null){
                    Text(
                        text = getDetail(cat = cat) + ".",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Text(text = cat.wikipedia_url + "", color = Color.White, fontSize = 20.sp)
                    Row {
                        Text(text = stringResource(id = R.string.cat_indoor))
                        RatingBar(cat.indoor.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_adaptability))
                        RatingBar(cat.adaptability.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_affection_level))
                        RatingBar(cat.affection_level.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_child_friendly))
                        RatingBar(cat.child_friendly.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_cat_friendly))
                        RatingBar(cat.cat_friendly.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_dog_friendly))
                        RatingBar(cat.dog_friendly.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_energy_level))
                        RatingBar(cat.energy_level.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_grooming))
                        RatingBar(cat.grooming.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_health_issues))
                        RatingBar(cat.health_issues.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_intelligence))
                        RatingBar(cat.intelligence.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_shedding_level))
                        RatingBar(cat.shedding_level.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_social_needs))
                        RatingBar(cat.social_needs.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_stranger_friendly))
                        RatingBar(cat.stranger_friendly.toFloat(),modifier = Modifier.height(30.dp)
                        )
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_vocalisation))
                        RatingBar(cat.vocalisation.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_experimental))
                        RatingBar(cat.experimental.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_suppressed_tail))
                        RatingBar(cat.suppressed_tail.toFloat(), modifier = Modifier.height(30.dp))
                    }
                    Row {
                        Text(text = stringResource(id = R.string.cat_short_legs))
                        RatingBar(cat.short_legs.toFloat(), modifier = Modifier.height(30.dp))
                    }
                }

            }
        }
    }
}

@Composable
private fun getDetail(cat: Cat):String{
    var content: String = cat.name + "\n"
    content += cat.temperament+ "\n"
    content += cat.origin + "\n"
    content += cat.country_code+ "\n"
    content += cat.description + "\n"
    return content
}
