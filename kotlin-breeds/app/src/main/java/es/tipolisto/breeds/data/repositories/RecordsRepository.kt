package es.tipolisto.breeds.data.repositories

import android.content.Context
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.records.RecordDao
import es.tipolisto.breeds.data.database.records.RecordEntity

class RecordsRepository() {

    companion object{
        fun getFirst20(recordDao: RecordDao):List<RecordEntity>{
            return recordDao.getFirst20RecordEntities()
        }



        fun getFirst10RecordsByPosition(recordDao: RecordDao):List<RecordEntity>?{
            return recordDao.getFirst10RecordsByPosition()
        }
        fun getNewRecordPosition (recordDao: RecordDao,score:Int):RecordEntity?{
            return recordDao.getNewRecordPosition(score)
        }
        fun getFollowingRecords(recordDao: RecordDao,newPosition:Int):List<RecordEntity>?{
            return recordDao.getFollowingRecords(newPosition)
        }



        fun insert(recordDao: RecordDao,recordEntities: RecordEntity){
            recordDao.insert(recordEntities)
        }

        fun update(recordDao: RecordDao,recordEntity: RecordEntity){
            recordDao.update(recordEntity)
        }
    }
}