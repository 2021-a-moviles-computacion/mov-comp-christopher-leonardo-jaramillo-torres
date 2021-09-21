package com.gracegbe.firebase01

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.type.LatLng
import org.intellij.lang.annotations.Language

class FMapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_maps)

        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        fragmentoMapa.getMapAsync{ googleMap ->
            if (googleMap != null){
                mapa = googleMap
                establecerConfiguracionMapa()

                val epn = com.google.android.gms.maps.model.LatLng(-0.2101526533378791, -78.48867294893778)
                val titulo = "EPN"
                val zoom = 17f

                anadirMarcador(epn, titulo)
                moverCamaraConZoom(epn, zoom)

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

            escucharListeners()

        }

        val botonCarolina = findViewById<Button>(R.id.btn_ir_carolina)
        botonCarolina
            .setOnClickListener {
                val jardin = com.google.android.gms.maps.model.LatLng(-0.188734709200574, -78.4878697489378)
                val zoom = 17f
                moverCamaraConZoom(jardin, zoom)
            }

    }




    fun escucharListeners(){
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener")
        }

        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener")
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }

        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnCameraMoveListener")
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
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

    /*fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if(tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf( // Arreglo de permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), 1 // Codigo de peticion de los permisos
            )
        }
    }*/

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