package com.example.lahuelladeandrea.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/*Dao es una interfaz que permite interactuar con los datos almacenados en la base de datos de Room, usando querys SQL*/
@Dao
interface QADao {
    @Query("SELECT * FROM QA")
    fun getQuestions(): LiveData<List<QAModel>>

    @Insert
    fun insertQuestion(vararg question: QAModel)

    @Query("DELETE FROM QA WHERE questionId = :questionId")
    fun deleteQuestion(questionId: Int)

    @Query("UPDATE QA SET answer = :answer WHERE questionId = :questionId ")
    fun putAnswer(questionId: Int, answer: String)
}