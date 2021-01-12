package com.i4bchile.marsrover

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MarsViewModel : ViewModel() {
    val repository = Repository()
    val error = MutableLiveData<Boolean>()
    private val image = MutableLiveData<Bitmap>()
    private val urlMarsRoverImage = "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG"

    //Es la función que llama a la función que tiene el repositorio para descargar las imagenes (aquella que contiene coroutines)
    fun loadImages(): LiveData<Bitmap>{
        //Generamos una coroutine que va a buscar la función download a la clase Repository() y evalúa la respuesta (success o error)
        viewModelScope.launch {
            when (val download = repository.download(urlMarsRoverImage)) {
                //Si es success actualiza el valor del LiveData
                is Repository.Result.Success -> {
                    image.postValue(download. data)
                }
                else -> {
                    //Si no es success, devuelve un error
                    Log.d( "MainActivity" , download.toString())
                    error.postValue(true)
                }
            }
        }
        return image
    }
}