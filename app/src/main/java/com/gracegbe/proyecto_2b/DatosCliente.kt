package com.gracegbe.proyecto_2b

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class DatosCliente : AppCompatActivity() {

    val select_activity = 50
    var imageUri: Uri? = null
    var imagenCliente: ImageView? = null
    var mStorage: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_cliente)

        // Recuperando datos
        val nombreCliente = findViewById<TextView>(R.id.txt_datos_cliente_nombre)
        val fechaNacimiento = findViewById<TextView>(R.id.txt_datos_cliente_fecha_nacimiento)
        val descripcionCliente = findViewById<TextView>(R.id.txt_datos_cliente_descripcion)
        imagenCliente = findViewById<ImageView>(R.id.imgv_imagen_cliente)


        imagenCliente!!.setOnClickListener {
            abrirGaleria(this, select_activity)
        }


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
                Log.i(
                    "cliente",
                    "${Cliente.nombreUsuarioClienteAux} - ${Cliente.correoElectronicoClienteAux} - ${nombreCliente.text.toString()} - ${fechaNacimiento.text.toString()} - ${descripcionCliente.text.toString()}"
                )
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

    fun abrirGaleria(actividad: Activity, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        actividad.startActivityForResult(intent, code)
    }

    fun registrarCliente(datosCliente: HashMap<String, Any>) {

        val db = Firebase.firestore

        db.collection("cliente")
            .document(Cliente.nombreUsuarioClienteAux.toString())
            .set(datosCliente)

        val intent = Intent(this, InicioCliente::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == select_activity && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                imagenCliente!!.setImageURI(imageUri)
                mStorage = FirebaseStorage.getInstance().getReference()
                var filepaht: StorageReference = mStorage!!.child(Cliente.correoElectronicoClienteAux)
                filepaht.putFile(imageUri!!)
            }
        }
    }

}

