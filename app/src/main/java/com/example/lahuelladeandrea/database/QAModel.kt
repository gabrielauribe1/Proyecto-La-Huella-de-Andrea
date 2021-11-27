package com.example.lahuelladeandrea.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Esta clase es utilizada para cada registro de la base de datos.
@Entity(tableName = "qa")
class QAModel(
    var question: String,
    var answer: String,
    @PrimaryKey(autoGenerate = true) // Establecemos que los ids sean únicos y autoincrementables.
    var questionId: Int = 0,
): Serializable