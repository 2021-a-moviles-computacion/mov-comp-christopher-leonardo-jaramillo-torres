package com.gracegbe.examen2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gracegbe.examen.Servidor

class InsertarServidor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_servidor)
        val ubicacion = findViewById<EditText>(R.id.txt_ubicacion)
        val direccionIP = findViewById<EditText>(R.id.txt_direccionIp)
        val marca = findViewById<EditText>(R.id.txt_marca)
        val empresa = findViewById<EditText>(R.id.txt_empresa)
        val tipoServidor = findViewById<EditText>(R.id.txt_tipo_empresa)
        val protocolos = findViewById<EditText>(R.id.txt_protocolos)

        val botonNuevoServidor = findViewById<Button>(R.id.btn_boton_anadir_servidor)

        Log.i("editar", "${ServidorMemoria.actualizacionServidor}")

        if (ServidorMemoria.actualizacionServidor == true) {
            val db = Firebase.firestore

            db.collection("servidor")
                .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                .get()
                .addOnSuccessListener {
                    var servidor =
                        Servidor(
                            it.id.toString(),
                            it.get("ubicacion").toString(),
                            it.get("direccionIP").toString(),
                            it.get("marca").toString(),
                            it.get("tipoServidor").toString(),
                            it.get("protocolos").toString()
                        )

                    ubicacion.setText(servidor.ubicacion)
                    direccionIP.setText(servidor.direccionIP)
                    marca.setText(servidor.marca)
                    empresa.setText(servidor.empresa)
                    tipoServidor.setText(servidor.tipoServidor)
                    protocolos.setText(servidor.protocolos)
                }
        }


        botonNuevoServidor.setOnClickListener {

            val datosServidor = hashMapOf<String, Any>(
                "direccionIP" to direccionIP.text.toString(),
                "marca" to marca.text.toString(),
                "protocolos" to protocolos.text.toString(),
                "tipoServidor" to tipoServidor.text.toString(),
                "ubicacion" to ubicacion.text.toString()
            )
            
            if (ubicacion.text.isNotEmpty() &&
                    direccionIP.text.isNotEmpty() &&
                    marca.text.isNotEmpty() &&
                    empresa.text.isNotEmpty() &&
                    tipoServidor.text.isNotEmpty() &&
                    protocolos.text.isNotEmpty()){

                if (ServidorMemoria.actualizacionServidor == false) {

                    val db = Firebase.firestore
                    val servidor = db.collection("servidor")

                    servidor.document(empresa.text.toString()).set(datosServidor)
                    abrirActividad(MainActivity::class.java)
                }

                if (ServidorMemoria.actualizacionServidor == true) { // en caso de que toque actualizar

                    val db = Firebase.firestore
                    val servidorAct = db.collection("servidor").document(empresa.text.toString())
                    servidorAct.update(datosServidor)
                    ServidorMemoria.actualizacionServidor == false
                    abrirActividad(MainActivity::class.java)
                }
                
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

    fun abrirActividad(clase: Class<*>) {
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }
}