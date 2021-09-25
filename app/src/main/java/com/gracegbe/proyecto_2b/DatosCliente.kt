package com.gracegbe.proyecto_2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DatosCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_cliente)

        // Recuperando datos
        val nombreCliente = findViewById<TextView>(R.id.txt_datos_cliente_nombre)
        val fechaNacimiento = findViewById<TextView>(R.id.txt_datos_cliente_fecha_nacimiento)
        val descripcionCliente = findViewById<TextView>(R.id.txt_datos_cliente_descripcion)


        val registrar = findViewById<Button>(R.id.btn_registrar_cliente)

        registrar.setOnClickListener {
            // mapeando los datos

            if (nombreCliente.text.isNotEmpty() && fechaNacimiento.text.isNotEmpty() && descripcionCliente.text.isNotEmpty()){
                val datosCliente = hashMapOf<String, Any>(
                    "nombreCliente" to nombreCliente.text.toString(),
                    "correoElectronicoCliente" to Cliente.correoElectronicoClienteAux,
                    "fechaNacimientoCliente" to fechaNacimiento.text.toString(),
                    "descripcionCliente" to descripcionCliente.text.toString()
                )
                Log.i("cliente","${Cliente.nombreUsuarioClienteAux} - ${Cliente.correoElectronicoClienteAux} - ${nombreCliente.text.toString()} - ${fechaNacimiento.text.toString()} - ${descripcionCliente.text.toString()}")
                registrarCliente(datosCliente)
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

    fun registrarCliente(datosCliente: HashMap<String, Any>) {

        //Log.i("cliente","${Cliente.nombreUsuarioClienteAux.toString()} - ${Cliente.correoElectronicoClienteAux.toString()}")

        val db = Firebase.firestore

        db.collection("cliente")
            .document(Cliente.nombreUsuarioClienteAux.toString())
            .set(datosCliente)

        val intent = Intent(this, InicioCliente::class.java)
        startActivity(intent)
    }

}