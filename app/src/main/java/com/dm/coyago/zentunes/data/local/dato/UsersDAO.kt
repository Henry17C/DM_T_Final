package com.dm.coyago.zentunes.data.local.dato

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dm.coyago.zentunes.data.local.entities.Users

@Dao
interface UsersDAO {

    @Query("Select * from Users ")
    fun getAllUsers():List<Users>


    @Query("Select * from Users where id = :id")
    fun getSingleUser(id: Int):Users

    @Insert
    fun insertUser(usersList:List<Users>)

    @Insert
    fun insertSingleUser(user:Users)
    @Update
    fun updateUse(usersList:List<Users>)

    @Delete
    fun deleteUser(user:Users)

}