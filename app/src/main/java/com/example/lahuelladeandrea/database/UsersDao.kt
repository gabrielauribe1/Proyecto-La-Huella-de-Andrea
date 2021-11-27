package com.example.lahuelladeandrea.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// El Dao es una interfaz que nos permite interactuar con los datos almacenados en la BD de Room, usando SQL
@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getAllUsers(): LiveData<List<UserModel>>

    @Insert
    fun insertUser(vararg user: UserModel)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()

    @Query("SELECT * FROM Users WHERE email LIKE :email AND password LIKE :password ")
    fun login(email: String, password: String): UserModel?

    @Query("SELECT * FROM Users WHERE email LIKE :email")
    fun validationUser(email: String): UserModel?
}