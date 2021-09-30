package com.gracegbe.proyecto_2b

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class DatosRestaurante : AppCompatActivity() {

    val select_activity = 50
    var imageUri: Uri? = null
    var imagenRestaurante: ImageView? = null
    var mStorage: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_restaurante)

        // recuperando datos
        val nombreRestaurante = findViewById<TextView>(R.id.txt_datos_restaurante_nombre)
        val horario = findViewById<TextView>(R.id.txt_datos_restaurante_horario)
        val celular = findViewById<TextView>(R.id.txt_datos_restaurante_numero_celular)
        val direccion = findViewById<TextView>(R.id.txt_datos_restaurante_direccion)
        val descripcion = findViewById<TextView>(R.id.txt_datos_restaurante_descripcion)

        imagenRestaurante = findViewById<ImageView>(R.id.imgv_imagen_restaurante)
        imagenRestaurante!!.setOnClickListener {
            abrirGaleria(this, select_activity)
        }

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

    fun abrirGaleria(actividad: Activity, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        actividad.startActivityForResult(intent, code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == select_activity && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                imagenRestaurante!!.setImageURI(imageUri)
                mStorage = FirebaseStorage.getInstance().getReference()
                var filepaht: StorageReference = mStorage!!.child(Restaurante.correoElectronicoRestauranteAux)
                filepaht.putFile(imageUri!!)
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