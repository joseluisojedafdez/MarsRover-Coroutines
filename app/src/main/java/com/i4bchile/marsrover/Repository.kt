package com.i4bchile.marsrover

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL


class Repository {
    //Es una clase que solamente contiene 2 tipos de resultado: Success y Error
    sealed class Result<out R> {
        //Succes devuelve algo
        data class Success<out T>(val data: T) : Result<T>()
        //Error devuelve nada
        data class Error(val exception: Exception) : Result<Nothing>()
    }

    suspend fun download(url: String): Result<Bitmap> = withContext(Dispatchers.IO) {

        try {
            //En caso de éxito de la descarga de imagen desde internet
            val bmp: Bitmap?
            val inputStream = URL(url).openStream()
            bmp = BitmapFactory.decodeStream(inputStream)
            Log.d("Download", "download: success: $url")
            Result.Success(bmp)

        } catch (e: Exception) {

            //En caso de que hubiera un error en la descarga
            Log.d("Download", "download: failure $e ")
            e.printStackTrace()
            Result.Error(e) } }

}