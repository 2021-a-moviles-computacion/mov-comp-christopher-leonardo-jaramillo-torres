package com.gracegbe.examen2b

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class UbicacionPagina : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion_pagina)

        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val latitud = ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].latitud.toDouble()
        val longitud = ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].longitud.toDouble()
        val nombre = ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre
        val tituloMapa = findViewById<TextView>(R.id.lb_ubicacion_pagina)
        tituloMapa.setText(nombre)


        fragmentoMapa.getMapAsync{ googleMap ->
            if (googleMap != null){
                mapa = googleMap
                establecerConfiguracionMapa()

                val ubicacion = com.google.android.gms.maps.model.LatLng(latitud, longitud)
                val titulo = nombre
                val zoom = 8f

                anadirMarcador(ubicacion, titulo)
                moverCamaraConZoom(ubicacion, zoom)

            }

        }

    }

    fun anadirMarcador(pos: com.google.android.gms.maps.model.LatLng, title: String){
        mapa.addMarker(
            MarkerOptions()
                .position(pos)
                .title(title)
        )
    }

    fun moverCamaraConZoom(pos: com.google.android.gms.maps.model.LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(pos, zoom)
        )
    }

    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = androidx.core.content.ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )

            val tienePermisos = permisosFineLocation == android.content.pm.PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }


}
