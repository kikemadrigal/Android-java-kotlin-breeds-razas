
package es.tipolisto.breeds.ui.views

import android.content.Context
import android.content.ContextWrapper
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import es.tipolisto.breeds.R
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
import es.tipolisto.breeds.ui.viewModels.LoginViewModel
import es.tipolisto.breeds.ui.viewModels.RecordsViewModel
import es.tipolisto.breeds.utils.MediaPlayerClient
import es.tipolisto.breeds.utils.Util.Companion.getDate
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    /*val catsViewModel: CatsViewModel by viewModels()
    val fishViewModel: FishViewModel by viewModels()
    val dogsViewModel: DogsViewModel by viewModels()
    val loginViewModel:LoginViewModel by viewModels()*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mediaPlayerClient=MediaPlayerClient( applicationContext )
        val catsViewModel: CatsViewModel by viewModels()
        val fishViewModel: FishViewModel by viewModels()
        val dogsViewModel: DogsViewModel by viewModels()
        val loginViewModel:LoginViewModel by viewModels()
        setContent {
            val context=LocalContext.current
            var isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}
            //val mediaPlayerClient by remember{mutableStateOf(MediaPlayerClient(context))}
            BreedsTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //3.Inicializamos el sonido
                    //val mediaPlayerClient=MediaPlayerClient(applicationContext)


                    //1. Obtenemos los records
                    val recordsDao=AppDataBaseClient.getRecordsDao(applicationContext)
                    val recordsViewModel=RecordsViewModel(recordsDao)
                    getRecords(recordsViewModel, recordsDao)
                    //2.creamos la base de datos e inicilizamos las clases que necesitas los dao
                    val favoritesDao=AppDataBaseClient.getFavoritesDao(applicationContext)
                    val favoritesViewModel=FavoritesViewModel(favoritesDao)

                    //4.Inicializamos la navegación
                    AppNavigation(
                        catsViewModel,
                        dogsViewModel,
                        fishViewModel,
                        favoritesViewModel,
                        recordsViewModel,
                        loginViewModel,
                        mediaPlayerClient
                    )
               }
            }
        }
    }



    private fun getRecords(recordsViewModel: RecordsViewModel, recordDao:RecordDao) {
        lifecycleScope.launch {
            val listScores=recordsViewModel.getLas10()
            //Si no hay records, creamos los records dentro de la tabla a a partir de un hasmap guardado en código
            if (listScores.size == 0) {
                val hashMapRecordList: HashMap<Int, String> = ArrayDataSourceProvider.getMapRecordList()
                Log.d("TAG", "Main activity dice: el hassmap tiene: "+hashMapRecordList.size)
                var nameUser:String?=null
                val iterator: Iterator<Int> = hashMapRecordList.keys.iterator()
                while (iterator.hasNext()) {
                    val points = iterator.next()
                    nameUser = hashMapRecordList[points]
                    val recordEntity= RecordEntity(null,nameUser!!, getDate(),points)
                    recordsViewModel.insertInDataBase(recordEntity)
                    Log.d("TAG", "Main activity dice: añadiendo "+recordEntity.toString())
                }
            }//Final lifecicle scope
        }

    }


    /*fun Context.getActivity(): ComponentActivity? = when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }*/

}
