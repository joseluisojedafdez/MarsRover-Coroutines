package com.i4bchile.marsrover

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.i4bchile.marsrover.databinding.ActivityMainBinding

/*
[x] 1) Crear proyecto android, api mínima 19, targetSdk 26 o superior.
[x] 2) Modificar diseño layout por defecto activity_main.xml para incluir las vistas requeridas.
        [x] 4 ProgressBar
        [x] 4 ImageView
        [] 1 Button

[] 3) Alinear las vistas dentro del ConstraintLayout para que estén centradas y donde se esperan.
[] 4) Agregar acción del botón dentro del método onCreate() para que crear un AlertDialog que muestre
    los mensajes solicitados.
[] 5) Crear el método downloadImages().
[] 6) Crear el método downloadImagesNasaRover(url).
[X] 7) Agregar permisos de internet al manifest y parámetros que sean necesarios (en caso de que la imagen no cargue
    usesCleartextTraffic.
[] 8) Compilar.
[] 9) Correr aplicación en emulador o dispositivo android con usb.

 */


class MainActivity : AppCompatActivity() {

    val marsViewModel: MarsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        binding.pbarProgress2.visibility=View.GONE
        binding.progressBar3.visibility=View.GONE
        binding.progressBar4.visibility=View.GONE
        marsViewModel.loadImages()
        marsViewModel.getImage1().observe(this, {
            updateView(it)
        })
        marsViewModel.getImage2().observe(this, {
            updateView2(it)
        })
        marsViewModel.getImage3().observe(this, {
            updateView3(it)
        })
        marsViewModel.getImage4().observe(this, {
            updateView4(it)
        })
        setContentView(binding.root)
    }

    private fun updateView(it: Bitmap?) {
        binding.ivImage1.setImageBitmap(it)
        binding.pbarProgress1.visibility = View.GONE
        binding.pbarProgress2.visibility=View.VISIBLE
    }
    private fun updateView2(it: Bitmap?) {
        binding.ivImage2.setImageBitmap(it)
        binding.pbarProgress2.visibility = View.GONE
        binding.progressBar3.visibility=View.VISIBLE
    }
    private fun updateView3(it: Bitmap?) {
        binding.ivImage3.setImageBitmap(it)
        binding.progressBar3.visibility = View.GONE
        binding.progressBar4.visibility=View.VISIBLE
    }
    private fun updateView4(it: Bitmap?) {
        binding.ivImage4.setImageBitmap(it)
        binding.progressBar4.visibility = View.GONE
    }

}