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
    private val image2 = MutableLiveData<Bitmap>()
    private val image3 = MutableLiveData<Bitmap>()
    private val image4 = MutableLiveData<Bitmap>()
    private val urlList = listOf(
        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RLB_486265291EDR_F0481570RHAZ00323M_.JPG",
        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RRB_486265291EDR_F0481570RHAZ00323M_.JPG"
    )

    //Es la función que llama a la función que tiene el repositorio para descargar las imagenes (aquella que contiene coroutines)
    fun loadImages() {
        //Generamos una coroutine que va a buscar la función download a la clase Repository() y evalúa la respuesta (success o error)
        viewModelScope.launch {

            Log.d("ViewMOdel", "loadImages: lanzando la corutina")
            for (i in 0..3) {
                when (val download = repository.download(urlList[i])) {
                    //Si es success actualiza el valor del LiveData
                    is Repository.Result.Success -> {
                        when (i) {
                            0 -> image.postValue(download.data)
                            1 -> image2.postValue(download.data)
                            2 -> image3.postValue(download.data)
                            3 -> image4.postValue(download.data)

                        }

                    }
                    else -> {
                        //Si no es success, devuelve un error
                        Log.d("MainActivity", download.toString())
                        error.postValue(true)
                    }
                }
            }

        }
    }

    fun getImage1(): LiveData<Bitmap> = image
    fun getImage2(): LiveData<Bitmap> = image2
    fun getImage3(): LiveData<Bitmap> = image3
    fun getImage4(): LiveData<Bitmap> = image4


}