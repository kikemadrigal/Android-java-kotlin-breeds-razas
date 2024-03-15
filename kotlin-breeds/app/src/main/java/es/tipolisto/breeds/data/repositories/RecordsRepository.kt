package es.tipolisto.breeds.data.repositories

import android.content.Context
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.records.RecordDao
import es.tipolisto.breeds.data.database.records.RecordEntity

class RecordsRepository() {

    companion object{
        fun getLast10(recordDao: RecordDao):List<RecordEntity>{
            return recordDao.getLast10RecordEntities()
        }

        fun insert(recordDao: RecordDao,recordEntities: RecordEntity){
            recordDao.insert(recordEntities)
        }


    }
}