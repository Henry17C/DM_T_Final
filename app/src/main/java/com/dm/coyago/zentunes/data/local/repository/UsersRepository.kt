package com.dm.coyago.zentunes.data.local.repository

import com.dm.coyago.zentunes.data.local.entities.Users

class UsersRepository {

    fun getListUsers():List<Users>{
        var lstUsers= listOf(
            Users("henry","12345","Henry","Coyago"),
            Users("ibeth","12345","Ibeth","Coyago")
        )
        return lstUsers
    }

}