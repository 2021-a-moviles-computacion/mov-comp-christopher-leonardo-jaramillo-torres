package com.gracegbe.proyecto_2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DatosRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_restaurante)

        // recuperando datos
        val nombreRestaurante = findViewById<TextView>(R.id.txt_datos_restaurante_nombre)
        val horario = findViewById<TextView>(R.id.txt_datos_restaurante_horario)
        val celular = findViewById<TextView>(R.id.txt_datos_restaurante_numero_celular)
        val direccion = findViewById<TextView>(R.id.txt_datos_restaurante_direccion)
        val descripcion = findViewById<TextView>(R.id.txt_datos_restaurante_descripcion)

        val registrar = findViewById<Button>(R.id.btn_registrar_restaurante)

        registrar.setOnClickListener {


            if (nombreRestaurante.text.isNotEmpty() &&
                    horario.text.isNotEmpty() &&
                    celular.text.isNotEmpty() &&
                    direccion.text.isNotEmpty() &&
                    descripcion.text.isNotEmpty()){
                val datosRestaurante = hashMapOf<String, Any>(
                    "nombreRestaurante" to nombreRestaurante.text.toString(),
                    "horarioAtencion" to horario.text.toString(),
                    "numeroCelular" to celular.text.toString(),
                    "direccionRestaurante" to direccion.text.toString(),
                    "descripcionRestaurante" to descripcion.text.toString(),
                    "correoElectronicoRestaurante" to Restaurante.correoElectronicoRestauranteAux.toString()
                )
                registrarRestaurante(datosRestaurante)

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

    fun registrarRestaurante(datosRestaurante: HashMap<String, Any>) {
        val db = Firebase.firestore

        db.collection("restaurante")
            .document(Restaurante.nombreUsuarioRestauranteAux.toString())
            .set(datosRestaurante)

        val intent = Intent(this, InicioRestaurante::class.java)
        startActivity(intent)
    }

}