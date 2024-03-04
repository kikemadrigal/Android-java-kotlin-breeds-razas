
package es.tipolisto.breeds.ui.views

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.records.RecordDao
import es.tipolisto.breeds.data.database.records.RecordEntity
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.data.providers.ArrayDataSourceProvider
import es.tipolisto.breeds.data.repositories.RecordsRepository
import es.tipolisto.breeds.ui.navigation.AppNavigation
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.CatsViewModel
import es.tipolisto.breeds.ui.viewModels.DogsViewModel
import es.tipolisto.breeds.ui.viewModels.FavoritesViewModel
import es.tipolisto.breeds.ui.viewModels.FishViewModel
import kotlinx.coroutines.launch
import java.util.Date


class MainActivity : ComponentActivity() {
    val catsViewModel: CatsViewModel by viewModels()
    val fishViewModel: FishViewModel by viewModels()
    val dogsViewModel: DogsViewModel by viewModels()
    val favoritesViewModel:FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreedsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //1. Obtenemos los records
                    getRecords()
                    //2.Inicializamos la navegación
                    //val catsViewModel= CatsViewModel()
                    AppNavigation(
                        catsViewModel,
                        dogsViewModel,
                        fishViewModel,
                        favoritesViewModel
                    )
               }
            }
        }
    }



    private fun getRecords() {
        lifecycleScope.launch {
            val listScores=RecordsRepository.getLast10(applicationContext)
            //Si no hay records, creamos los records dentro de la tabla a a partir de un hasmap guardado en código
            if (listScores.size == 0) {
                val hashMapRecordList: HashMap<Int, String> = ArrayDataSourceProvider.getMapRecordList()
                Log.d("TAG", "Main activity dice: el hassmap tiene: "+hashMapRecordList.size)
                var nameUser:String?=null
                val iterator: Iterator<Int> = hashMapRecordList.keys.iterator()
                while (iterator.hasNext()) {
                    val points = iterator.next()
                    nameUser = hashMapRecordList[points]
                    val recordEntity: RecordEntity = RecordEntity(null,nameUser!!,Date().toString(),points)
                    RecordsRepository.insert(applicationContext,recordEntity)
                    Log.d("TAG", "Main activity dice: añadiendo "+recordEntity.toString())
                }
            }//Final lifecicle scope
        }

    }




}
