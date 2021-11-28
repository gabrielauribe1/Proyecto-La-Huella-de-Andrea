package com.example.lahuelladeandrea.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/*Esta clase es utilizada para cada pregunta que hace el usuario en el campo de Q&A de la base de datos y la respuesta que se obtiene por el admin.*/
@Entity(tableName = "qa")
class QAModel(
    var question: String,
    var answer: String,
    @PrimaryKey(autoGenerate = true) /*Se establecen ids únicos y autoincrementables.*/
    var questionId: Int = 0,
): Serializable