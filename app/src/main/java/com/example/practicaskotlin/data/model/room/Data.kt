package com.example.practicaskotlin.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
class Data (

    @ColumnInfo(name = "dataEnglish")
    val dataEnglish : String ,

    @ColumnInfo(name = "dataSpanish")
    val  dataSpanish : String
)

{
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
