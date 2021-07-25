package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.Servidor

class InsertarServido : AppCompatActivity() {

    var servidorAux: Servidor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_servido)

        ServidorMemoria.baseDatos = BaseDatosServidor(this)

        
        val ubicacion = findViewById<EditText>(R.id.txt_ubicacion)
        val direccionIP = findViewById<EditText>(R.id.txt_direccionIp)
        val marca = findViewById<EditText>(R.id.txt_marca)
        val empresa = findViewById<EditText>(R.id.txt_empresa)
        val tipoServidor = findViewById<EditText>(R.id.txt_tipo_empresa)
        val protocolos = findViewById<EditText>(R.id.txt_protocolos)

        val botonNuevoServidor = findViewById<Button>(R.id.btn_boton_anadir_servidor)

        if(ServidorMemoria.actualizacionServidor == true) {
            //actualizarDatos(ServidorMemoria.servidorActualizar)
            ubicacion.setText(ServidorMemoria.servidorActualizar?.ubicacion)
            direccionIP.setText(ServidorMemoria.servidorActualizar?.direccionIP)
            marca.setText(ServidorMemoria.servidorActualizar?.marca)
            empresa.setText(ServidorMemoria.servidorActualizar?.empresa)
            tipoServidor.setText(ServidorMemoria.servidorActualizar?.tipoServidor)
            protocolos.setText(ServidorMemoria.servidorActualizar?.protocolos)

        }


        botonNuevoServidor.setOnClickListener {

        if (ServidorMemoria.actualizacionServidor == false) {
                if (ServidorMemoria != null) {
                    Log.i("ser", "Entro al primer if")
                    ServidorMemoria.indexBase = ServidorMemoria.arregloServidores.size
                    Log.i("ver", "index guardado ${ServidorMemoria.indexBase}")
                    ServidorMemoria.baseDatos?.insertarServidor(
                        ServidorMemoria.indexBase,
                        ubicacion.text.toString(),
                        direccionIP.text.toString(),
                        marca.text.toString(),
                        empresa.text.toString(),
                        tipoServidor.text.toString(),
                        protocolos.text.toString()
                    )

                    ubicacion.setText("")
                    direccionIP.setText("")
                    marca.setText("")
                    empresa.setText("")
                    tipoServidor.setText("")
                    protocolos.setText("")
                    
                }

                abrirActividad(MainActivity::class.java)
            }

            if (ServidorMemoria.actualizacionServidor == true) {

                //ServidorMemoria.arregloServidores.remove(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado])

                if (ServidorMemoria != null) {

                    Log.i("act", "index ${ServidorMemoria.idServidorArraySelecionado}  ----  esto se va a enviar a la base ${ubicacion.text.toString()}")

                    val salio = ServidorMemoria.baseDatos?.actualizarServidor(
                        ServidorMemoria.idServidorArraySelecionado,
                        ubicacion.text.toString(),
                        direccionIP.text.toString(),
                        marca.text.toString(),
                        empresa.text.toString(),
                        tipoServidor.text.toString(),
                        protocolos.text.toString()
                    )


                    Log.i("act", "salio -> ${salio}")

                    servidorAux = Servidor(ServidorMemoria.idServidorArraySelecionado, ubicacion.text.toString(), direccionIP.text.toString(), marca.text.toString(), empresa.text.toString()
                    , tipoServidor.text.toString(), protocolos.text.toString())
                }

                actualizarDatos(servidorAux)

                abrirActividad(MainActivity::class.java)

                ServidorMemoria.actualizacionServidor = false
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
        if (servidorAct != null) {
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
        }

    }
}