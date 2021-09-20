package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.Servidor

class InsertarServido : AppCompatActivity() {

    var servidorAux: Servidor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_servido)

        //ServidorMemoria.baseDatos = BaseDatosServidor(this)


        val ubicacion = findViewById<EditText>(R.id.txt_ubicacion)
        val direccionIP = findViewById<EditText>(R.id.txt_direccionIp)
        val marca = findViewById<EditText>(R.id.txt_marca)
        val empresa = findViewById<EditText>(R.id.txt_empresa)
        val tipoServidor = findViewById<EditText>(R.id.txt_tipo_empresa)
        val protocolos = findViewById<EditText>(R.id.txt_protocolos)

        val botonNuevoServidor = findViewById<Button>(R.id.btn_boton_anadir_servidor)

        Log.i("editar","${ServidorMemoria.actualizacionServidor}")

        if(ServidorMemoria.actualizacionServidor == true) {
            val db = Firebase.firestore

            db.collection("servidor")
                .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                .get()
                .addOnSuccessListener {
                    var servidor =
                        Servidor(it.id.toString(), it.get("ubicacion").toString(), it.get("direccionIP").toString(), it.get("marca").toString(), it.get("tipoServidor").toString(), it.get("protocolos").toString())

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

        if (ServidorMemoria.actualizacionServidor == false) {

            val db = Firebase.firestore
            val servidor = db.collection("servidor")

            servidor.document(empresa.text.toString()).set(datosServidor)
            abrirActividad(MainActivity::class.java)
        }

        if (ServidorMemoria.actualizacionServidor == true) { // en caso de que toque actualizar

            /*val ubicacionS = findViewById<EditText>(R.id.txt_ubicacion)
            val direccionIPS = findViewById<EditText>(R.id.txt_direccionIp)
            val marcaS = findViewById<EditText>(R.id.txt_marca)
            val empresaS = findViewById<EditText>(R.id.txt_empresa)
            val tipoServidorS = findViewById<EditText>(R.id.txt_tipo_empresa)
            val protocolosS = findViewById<EditText>(R.id.txt_protocolos)

            val datosServidorS = hashMapOf<String, Any>(
                "direccionIP" to direccionIPS.text.toString(),
                "marca" to marcaS.text.toString(),
                "protocolos" to protocolosS.text.toString(),
                "tipoServidor" to tipoServidorS.text.toString(),
                "ubicacion" to ubicacionS.text.toString()
            )*/

            val db = Firebase.firestore
            val servidorAct = db.collection("servidor").document(empresa.text.toString())
            servidorAct.update(datosServidor)
            ServidorMemoria.actualizacionServidor == false
            abrirActividad(MainActivity::class.java)
        }

        }


    }

    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun actualizarDatos(servidorAct: Servidor?){
        /*if (servidorAct != null) {
            //Log.i("act","Entro al if para actualizar ${ServidorMemoria.idServidorArraySelecionado}")

            //Log.i("act","ubicacion antes - ${ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].ubicacion}")
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].ubicacion = servidorAct.ubicacion


            //Log.i("act","el que deberia estar - ${servidorAct.ubicacion }")

            //Log.i("act","ubicaciones despues - ${ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].ubicacion }")
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].direccionIP = servidorAct.direccionIP
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].marca = servidorAct.marca
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa = servidorAct.empresa
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].tipoServidor = servidorAct.tipoServidor
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].protocolos = servidorAct.protocolos
        }*/

    }
}