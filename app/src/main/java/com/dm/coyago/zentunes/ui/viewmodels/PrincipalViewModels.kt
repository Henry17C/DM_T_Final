package com.dm.coyago.zentunes.ui.viewmodels
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.coyago.zentunes.data.network.entities.openwhyd.Track
import com.dm.coyago.zentunes.logic.openwhyd.GetAllMusicRecomendations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrincipalViewModels : ViewModel() {

    val listItems = MutableLiveData<List<Track>>()
    val error = MutableLiveData<String>()

    val getAllMusicRecomendations = GetAllMusicRecomendations()
///uso de ktor KTOR

fun getAllNobelPrizes() {

        viewModelScope.launch(Dispatchers.IO) {
            val response= getAllMusicRecomendations.invoke()
          if(response.isNotEmpty()){
              listItems.postValue(response)
          }else{
              error.postValue("Ocurrio un erro al llamar a la API")
          }


        }
    }



}
