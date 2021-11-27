package com.example.lahuelladeandrea.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// El Dao es una interfaz que nos permite interactuar con los datos almacenados en la BD de Room, usando SQL
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