package es.tipolisto.breeds.data.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.tipolisto.breeds.data.model.RecordEntity;

@Dao
public interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(RecordEntity user);

    @Query("SELECT * FROM RecordEntity WHERE id=(:userEntityId)")
    RecordEntity getIdRecord(int userEntityId);

    @Query("SELECT * FROM RecordEntity WHERE name LIKE (:userEntityName)")
    RecordEntity getNameRecord(String userEntityName);

    @Query("SELECT * FROM RecordEntity")
    List<RecordEntity> getAllRecordEntities();

    //Obtener los últimos 10 registros ordenados por record de mayor a menor
    @Query("SELECT * FROM RecordEntity ORDER  BY score DESC LIMIT 10")
    List<RecordEntity> getLast10RecordEntities();

    @Query("SELECT * FROM RecordEntity WHERE name IN (:name)")
    List<RecordEntity> loadAllByIds(int[] name);


    @Insert
    void insertAll(RecordEntity... users);

    @Update
    void update(RecordEntity user);


    @Delete
    void delete(RecordEntity user);

    //Borramos todos los que sean menores de una puntuación
    @Query("DELETE FROM RecordEntity WHERE SCORE<(:score)")
    int deleteAllMinor(int score);
}