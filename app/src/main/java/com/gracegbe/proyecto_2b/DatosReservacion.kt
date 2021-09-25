package com.gracegbe.proyecto_2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DatosReservacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_reservacion)

        // obtener la informacion

        val nombreRestaurante = findViewById<TextView>(R.id.txtv_titulo_restaurante2)
        val fechaReservacion = findViewById<TextView>(R.id.txt_reservacion_fecha)
        val horaReservacion = findViewById<TextView>(R.id.txt_reservacion_hora)
        val numPersonasReservacion = findViewById<TextView>(R.id.txt_reservacion_numero_personas)
        val usuarioCliente = intent.getStringExtra("usuarioCliente")
        val usuarioRestaurante = intent.getStringExtra("usuarioRestaurante")

        nombreRestaurante.text = intent.getStringExtra("nombreRestaurante")

        val reservar = findViewById<Button>(R.id.btn_reservar)

        reservar.setOnClickListener {

            if (fechaReservacion.text.isNotEmpty() &&
                    horaReservacion.text.isNotEmpty() &&
                    numPersonasReservacion.text.isNotEmpty())
                    {
                        val datosReservacion = hashMapOf<String, Any>(
                            "nombreUsuarioCliente" to usuarioCliente.toString(),
                            "nombreUsuarioRestaurante" to usuarioRestaurante.toString(),
                            "fechaReservacion" to fechaReservacion.text.toString(),
                            "horaReservacion" to horaReservacion.text.toString(),
                            "numeroPersonas" to numPersonasReservacion.text.toString(),
                            "estado" to "pendiente"
                        )

                        registrarReservacion(datosReservacion, usuarioCliente.toString(), usuarioRestaurante.toString())

                        val intent = Intent(this, InicioCliente::class.java)
                        startActivity(intent)
            } else {
                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("Error")
                alerta.setMessage("LLene todos los campos")
                alerta.setPositiveButton("Aceptar", null)
                val dialog: AlertDialog = alerta.create()
                dialog.show()
            }

        }
    }

    fun registrarReservacion(datosReservacion: HashMap<String, Any>, cliente: String, restaurante: String){
        val db = Firebase.firestore
        val documento = "${cliente}${restaurante}"

        db.collection("reservacion")
                .document(documento)
                .set(datosReservacion)

        val intent = Intent(this, InicioCliente::class.java)
        startActivity(intent)
    }


}