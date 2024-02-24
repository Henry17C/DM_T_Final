package com.dm.coyago.zentunes.logic.local.login

import android.content.Context
import android.util.Log
import com.dm.coyago.zentunes.data.local.entities.Users
import com.dm.coyago.zentunes.data.local.repository.DBRepository
import com.dm.coyago.zentunes.data.local.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LoginUserCase  (val connection: DBRepository){

    suspend fun checkLoginReturn(email: String, password: String): Int {
        var ret = -1

        // Ejecuta la operación getAllUser en el hilo de entrada/salida (Dispatchers.IO)
        val users = withContext(Dispatchers.IO) {
            getAllUser()

        }
        Log.d("USER", users.get(5).toString() )
        val lstUsers = users.filter { it.email == email && it.password == password }

        if (lstUsers.isNotEmpty()) {
            ret = lstUsers.first().id
        }

        // Loguea el resultado
        Log.d("NOMBRE", ret.toString())

        return ret  // Devuelve el resultado de la función
    }
    suspend fun insertUsers(){

        if(connection.getUserDAO().getAllUsers().isEmpty()){
            connection.getUserDAO().insertUser(UsersRepository().getListUsers())
        }else{

        }

    }

    suspend fun insertSingleUser(user: Users){
        connection.getUserDAO().insertSingleUser(user)

    }

    suspend fun getUserNameDB(id:Int, context: Context): Users {

        return   connection.getUserDAO().getSingleUser(id)

    }





    suspend fun getAllUser():  List<Users>{
       return connection.getUserDAO().getAllUsers()

    }




}