package com.gracegbe.examen01

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
                val zoom = 17f

                anadirMarcador(ubicacion, titulo)
                moverCamaraConZoom(ubicacion, zoom)

                /*val poliLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                com.google.android.gms.maps.model.LatLng(-0.1759187040647396, -78.48506472421384),
                                com.google.android.gms.maps.model.LatLng(-0.17632468492901104, -78.48265589308046),
                                com.google.android.gms.maps.model.LatLng(-0.17746143130181483, -78.4770533307815)
                            )
                    )

                val poligonoUno = googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                com.google.android.gms.maps.model.LatLng(-0.1771546902239471, -78.48344981495214),
                                com.google.android.gms.maps.model.LatLng(-0.17968981486125768, -78.48269198043828),
                                com.google.android.gms.maps.model.LatLng(-0.17710958124147777, -78.48142892291516)
                            )
                    )

                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2" // este es un id*/

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
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )

            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }


}