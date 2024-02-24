package com.dm.coyago.zentunes.data.local.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dm.coyago.zentunes.data.local.dato.UsersDAO
import com.dm.coyago.zentunes.data.local.entities.Users


@Database(
    entities = [Users::class],
    version = 4
)

abstract class DBRepository: RoomDatabase() {

    abstract fun getUserDAO(): UsersDAO;

}

class DBConnection(){

    fun getConnection(context: Context): DBRepository =
        Room.databaseBuilder(context, DBRepository::class.java, "DBText").build()

}