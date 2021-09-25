package com.gracegbe.proyecto_2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class VistaRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_restaurante)

        val tituloRestaurante = findViewById<TextView>(R.id.txtv_titulo_restaurante)
        val horarioRestaurante = findViewById<TextView>(R.id.txtv_horario_atencion)
        val numeroCelular = findViewById<TextView>(R.id.txtv_numero_celular)
        val direccionRestaurante = findViewById<TextView>(R.id.txtv_direccion_restaurante)
        val descripcionRestaurante = findViewById<TextView>(R.id.txtv_rec_descripcion_restaurante)

        // iniciar valores de restaurante
        val nombreRestaurante = intent.getStringExtra("nombreRestaurante")
        tituloRestaurante.text = intent.getStringExtra("nombreRestaurante")
        horarioRestaurante.text = intent.getStringExtra("horarioAtencion")
        numeroCelular.text = intent.getStringExtra("telefonoRestaurante")
        direccionRestaurante.text = intent.getStringExtra("direccionRestaurante")
        descripcionRestaurante.text = intent.getStringExtra("descripcionRestaurante")
        val correoRestaurante = intent.getStringExtra("correoRestaurante")

        val botonIrReservacion = findViewById<Button>(R.id.btn_ir_reservacion)

        botonIrReservacion.setOnClickListener {
            val intent = Intent(this, DatosReservacion::class.java)

            //Log.i("reser","${tituloRestaurante.text.toString()}")

            //intent.putExtra("nombreRestaurante", tituloRestaurante.text.toString())
            //intent.putExtra("nombreCliente", tituloRestaurante.text.toString())

            //aqui recupero el usuario restaurante
            Restaurante.listaRestaurante.forEach {
                Log.i("reser"," esto ${it.nombreUsuarioRestaurante}")
                Log.i("reser","debe ser igual a esto ${nombreRestaurante.toString()}")

                if(it.correoElectronicoRestaurante.equals(correoRestaurante)){
                    intent.putExtra("usuarioRestaurante", it.correoElectronicoRestaurante)
                    intent.putExtra("usuarioCliente", Cliente.usuarioNombreClienteSeteado)
                    Log.i("reser"," --> ${it.correoElectronicoRestaurante}")
                    Log.i("reser"," --> ${Cliente.usuarioNombreClienteSeteado}")
                }
            }

            startActivity(intent)
        }

    }
}