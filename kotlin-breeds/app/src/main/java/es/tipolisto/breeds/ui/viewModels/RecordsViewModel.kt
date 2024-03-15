package es.tipolisto.breeds.ui.viewModels

import androidx.lifecycle.ViewModel
import es.tipolisto.breeds.data.database.records.RecordDao
import es.tipolisto.breeds.data.database.records.RecordEntity
import es.tipolisto.breeds.data.repositories.RecordsRepository

class RecordsViewModel(private val recordDao:RecordDao):ViewModel() {
    fun getLas10():List<RecordEntity>{
        return RecordsRepository.getLast10(recordDao)
    }

    fun insertInDataBase(recordEntity: RecordEntity){
        RecordsRepository.insert(recordDao, recordEntity)
    }
}