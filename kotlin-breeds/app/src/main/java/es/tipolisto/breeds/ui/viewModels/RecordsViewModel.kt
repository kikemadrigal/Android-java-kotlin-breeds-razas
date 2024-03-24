package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import es.tipolisto.breeds.data.database.records.RecordDao
import es.tipolisto.breeds.data.database.records.RecordEntity
import es.tipolisto.breeds.data.providers.ArrayDataSourceProvider
import es.tipolisto.breeds.data.repositories.RecordsRepository
import es.tipolisto.breeds.utils.Util
import kotlinx.coroutines.launch


class RecordsViewModel(private val recordDao:RecordDao):ViewModel() {
    var _stateNewrecord = MutableLiveData<Boolean>()
    var stateNewrecord: LiveData<Boolean> = _stateNewrecord


    fun initializeRecords(){
        viewModelScope.launch {
            val listScores=getFirst20()
            //Si no hay records, creamos los records dentro de la tabla a a partir de un hasmap guardado en código
            if (listScores.size == 0) {
                val hashMapRecordList: LinkedHashMap<Int, String> = ArrayDataSourceProvider.getMapRecordList()
                Log.d("TAG", "Main activity dice: el hassmap tiene: "+hashMapRecordList.size)
                var position=1;
                var nameUser:String?=null
                val iterator: Iterator<Int> = hashMapRecordList.keys.iterator()
                while (iterator.hasNext()) {
                    val points = iterator.next()
                    nameUser = hashMapRecordList[points]
                    val recordEntity= RecordEntity(null,nameUser!!,points, position,
                        Util.getDate()
                    )
                    insertInDataBase(recordEntity)
                    position++;
                    Log.d("TAG", "Main activity dice: añadiendo "+recordEntity.toString())
                }
            }
        }

    }


    fun getFirst20():List<RecordEntity>{
        return RecordsRepository.getFirst20(recordDao)
    }


    fun insertNewRecord(name:String,score:Int){
        //Obtenemos la posición del anterior record que hay por encima
        val position=getNewRecordPosition(score)
        //Cambiamos las posiciones de los records que están por debajo
        changeRecordsPosition(position)
        insertInDataBase(RecordEntity(null,name,score,position, Util.getDate() ))
        //Log.d("TAG", "GameCatsScreen dice: Nuevo record de "+name)
        setRecord(false)
    }



    fun insertInDataBase(recordEntity: RecordEntity){
        RecordsRepository.insert(recordDao, recordEntity)
    }



    fun checkRecord(score:Int) {
        var lisLast10RecordEntity=RecordsRepository.getFirst10RecordsByPosition(recordDao)
        if(lisLast10RecordEntity!=null){
            lisLast10RecordEntity.forEachIndexed{index, record->
                //Si alguno delos records de la tabla es menor
                if(record.score<score){
                    Log.d("TAG","RecordsviewModel dice: el record por arriba mas proximo es el "+record.toString())
                    _stateNewrecord.value=true
                }
            }
        }else{
            Log.d("TAG","RecordsviewModel dice: No se obtuvo ningun record")
        }
    }

    fun getNewRecordPosition(score:Int):Int{
        var position=0
        var previousRecord=RecordsRepository.getNewRecordPosition(recordDao,score)
        if(previousRecord!=null)
            position=previousRecord.position
        return position+1
    }

    fun changeRecordsPosition(newPosition:Int){
        //Obtenemos el anterior al record actual
        var followingRecords=RecordsRepository.getFollowingRecords(recordDao, newPosition)

        if(followingRecords!=null){
            followingRecords.forEachIndexed{index, record->
                if(record!=null){
                    record.position+=1
                    RecordsRepository.update(recordDao, record)
                }
            }
        }
    }

    fun setRecord(value:Boolean){
        _stateNewrecord.value=value
    }
}

