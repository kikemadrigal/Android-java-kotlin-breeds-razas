package es.tipolisto.breeds.data.repositories

import android.content.Context
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.records.RecordEntity

class RecordsRepository(val db:AppDataBase) {

    companion object{
        fun getLast10(context: Context):List<RecordEntity>{
            return AppDataBaseClient.getRecordsDao(context = context).getLast10RecordEntities()
        }

        fun insert(context :Context,recordEntities: RecordEntity){
            AppDataBaseClient.getRecordsDao(context = context).insert(recordEntities)
        }


    }
}