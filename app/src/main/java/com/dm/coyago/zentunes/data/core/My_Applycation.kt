package com.dm.coyago.zentunes.data.core

import android.app.Application
import com.dm.coyago.zentunes.data.local.repository.DBConnection
import com.dm.coyago.zentunes.data.local.repository.DBRepository
import com.dm.coyago.zentunes.logic.local.login.LoginUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class My_Applycation: Application() {


    override fun onCreate() {
        super.onCreate()
        con = DBConnection().getConnection(applicationContext)

        GlobalScope.launch(Dispatchers.IO){

            LoginUserCase(con).insertUsers()

        }


    }

    //objeto que pertenece a la clase
    companion object{
        private lateinit var con: DBRepository

        fun getConnectionDB(): DBRepository {
            return con
        }
    }

}