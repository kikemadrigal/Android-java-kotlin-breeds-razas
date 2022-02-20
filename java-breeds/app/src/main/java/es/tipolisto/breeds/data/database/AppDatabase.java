package es.tipolisto.breeds.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.tipolisto.breeds.data.model.RecordEntity;

@Database(entities = {RecordEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    //Esto representa a la entidad o tabla usuarios
    public abstract RecordDao recordDao();
}