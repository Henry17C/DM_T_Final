package com.dm.coyago.zentunes.logic.openwhyd

import android.util.Log
import com.dm.coyago.zentunes.data.network.entities.openwhyd.MusicRecomendations
import com.dm.coyago.zentunes.data.network.entities.openwhyd.Track
import com.dm.coyago.zentunes.data.network.repository.KtorApiModule
import com.dm.coyago.zentunes.data.network.repository.RetrofitBase
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.isSuccess

class GetAllMusicRecomendations {

 suspend fun invoke():  List<Track>{
        var ret: List<Track> = ArrayList()

        runCatching {
            KtorApiModule.getKtorHttpClient().get{
                url(RetrofitBase.RECOMENDATIONS+"hot/electro?format=json")
            }
        }.onSuccess {
            if(it.status.isSuccess()){
                ret = it.body<MusicRecomendations>().tracks!!
            }
        }.onFailure {
            Log.d("TAG", it.message.toString())
        }
        return ret
    }


    }


