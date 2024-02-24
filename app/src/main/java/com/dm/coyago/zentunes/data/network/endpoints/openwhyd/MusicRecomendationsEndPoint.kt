package com.dm.coyago.zentunes.data.network.endpoints.openwhyd

import com.dm.coyago.zentunes.data.network.entities.openwhyd.MusicRecomendations
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicRecomendationsEndPoint {
    @GET("nobelPrizes")
    suspend fun getAllNobelPrizes(@Query("limit") limit: Int): Response<MusicRecomendations>
}