package es.tipolisto.breeds.data.database.records

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity
data class RecordEntity (
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "score") val score: Int
)
