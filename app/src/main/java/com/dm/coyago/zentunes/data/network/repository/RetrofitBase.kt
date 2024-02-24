package com.dm.coyago.zentunes.data.network.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {


    public const val RECOMENDATIONS = "https://openwhyd.org/"



    fun getMusicRecomendations(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RECOMENDATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

