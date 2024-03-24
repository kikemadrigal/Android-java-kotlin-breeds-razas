package es.tipolisto.breeds.data.database.records

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecordDao {
    //Dame todos los records
    @Query("SELECT * FROM RecordEntity")
    fun getAll(): List<RecordEntity>

    //Dame todos los records de un usuario con un nombre determinado
    @Query("SELECT * FROM RecordEntity WHERE name LIKE (:userEntityName)")
    fun getRecordByName(userEntityName: String): RecordEntity

    //Dame el record que sea mayor que la puntuación maxima que le pasamos de los últimos 10 records
    //@Query("SELECT * FROM RecordEntity WHERE score >= (:newScore) ORDER BY score ASC LIMIT 1")

    /*
    @Query("SELECT * FROM (SELECT * FROM RecordEntity ORDER BY score DESC LIMIT 10) WHERE score >= (:newScore) ORDER BY score ASC LIMIT 1")
    fun getRecordGreaterThanScore(newScore: Int): RecordEntity?
    */

    @Query("SELECT * FROM RecordEntity WHERE score >= (:newScore) ORDER BY score ASC LIMIT 1 ")
    fun getNewRecordPosition(newScore: Int): RecordEntity?

    @Query("SELECT * FROM RecordEntity WHERE position >= (:newPosition) ORDER BY score DESC")
    fun getFollowingRecords(newPosition: Int): List<RecordEntity>?
    @Query("SELECT * FROM RecordEntity WHERE position<11 ORDER BY score DESC LIMIT 10")
    fun getFirst10RecordsByPosition(): List<RecordEntity>?
    //Obtener los últimos 10 registros ordenados por record de mayor a menor
    @Query("SELECT * FROM RecordEntity ORDER BY score DESC LIMIT 21")
    fun getFirst20RecordEntities(): List<RecordEntity>

    //Dame todos los records de un usuarios a través del id
    @Query("SELECT * FROM RecordEntity WHERE id=(:userEntityName)")
    fun getRecordById(userEntityName: Int): RecordEntity



    @Query("SELECT * FROM RecordEntity WHERE name IN (:name)")
    fun loadAllByIds(name:Array<Int>): List<RecordEntity>

    //Obtener la maxima puntación
    @Query("SELECT max(score) FROM RecordEntity")
    fun getMaxRecordEntities(): Int





    @Insert
    fun insertAll(vararg recordEntities: RecordEntity)
    @Insert
    fun insert(recordEntities: RecordEntity)

    @Update
    fun update(recordEntity: RecordEntity)

    @Delete
    fun delete(recordEntity: RecordEntity)
}