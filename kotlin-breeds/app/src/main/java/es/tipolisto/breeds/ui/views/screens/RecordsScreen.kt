@file:OptIn(ExperimentalMaterial3Api::class)

package es.tipolisto.breeds.ui.views.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.records.RecordEntity
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.data.repositories.RecordsRepository
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.utils.MediaPlayerClient
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.coroutineContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(navController:NavController) {
    val context= LocalContext.current
    var isDarkMode by remember { mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context)) }
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Records", color= Color.White, fontWeight = FontWeight.Bold)
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
            val context = LocalContext.current
            LazyColumn(modifier=Modifier.padding(it)){
                val listRecords: List<RecordEntity> =RecordsRepository.getLast10(context)
                Log.d("TAG","RecordsScreen dice: enoontrados: "+listRecords.size+" records")
                if(listRecords.isEmpty())
                    Log.d("TAG","No hay records")
                else{
                    items(listRecords){
                            item->
                        listIntemRow(item, navController)
                    }
                }
            }
        }//Final de scafold
    }//Final de breedsTheme
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecordsScreenPreview() {
    BreedsTheme (darkTheme = false) {
        //ListRecordsScreen()
    }
}


@Composable
fun listIntemRow(recordEntity: RecordEntity, navController: NavController){
    Row(modifier= Modifier
        .padding(10.dp, 5.dp, 10.dp, 5.dp)
        .background(MaterialTheme.colorScheme.primary), verticalAlignment = Alignment.CenterVertically){
            Text(text = recordEntity.score.toString(), color= Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp )
            Text(text = recordEntity.name, color= Color.White, fontSize = 20.sp )
            Text(text = recordEntity.date, color= Color.White, fontSize = 16.sp )
    }
}

