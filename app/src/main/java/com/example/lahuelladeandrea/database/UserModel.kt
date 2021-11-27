package com.example.lahuelladeandrea.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Esta clase es utilizada para cada registro de la base de datos.
@Entity(tableName = "users")
class UserModel(
    val isAdmin: Boolean,
    val firstName: String,
    val lastName: String,
    var email: String,
    var password: String,
    @PrimaryKey(autoGenerate = true) // Establecemos que los ids sean únicos y autoincrementables.
    var userId: Int = 0,
): Serializable