package com.gracegbe.proyecto_2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class RegistroPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_principal)

        // Opciones de radiobutton
        val registroUsuario = findViewById<RadioButton>(R.id.rbo_cliente)
        val registroRestaurante = findViewById<RadioButton>(R.id.rbo_restaurante)

        // recuperando textos
        val nombreUsuario = findViewById<TextView>(R.id.txt_registro_nombre_usuario)
        val correoElectronico = findViewById<TextView>(R.id.txt_registro_correo_electronico)
        val contrasena = findViewById<TextView>(R.id.txt_registro_contrasena)

        val botonSiguienteDatos = findViewById<Button>(R.id.btn_ir_datos_cliente_restaurante)

        botonSiguienteDatos.setOnClickListener {

            if (nombreUsuario.text.isNotEmpty() && correoElectronico.text.isNotEmpty() && contrasena.text.isNotEmpty()) {

                if (registroUsuario.isChecked) {

                    Cliente.nombreUsuarioClienteAux = nombreUsuario.text.toString()
                    Cliente.correoElectronicoClienteAux = correoElectronico.text.toString()
                    Cliente.usuarioNombreClienteSeteado = nombreUsuario.text.toString()

                    val intent = Intent(this, DatosCliente::class.java)
                    crearUsuario(correoElectronico.text.toString(), contrasena.text.toString(), intent)

                } else if (registroRestaurante.isChecked) {

                    Restaurante.nombreRestauranteSeteado = nombreUsuario.text.toString()
                    Restaurante.nombreUsuarioRestauranteAux = nombreUsuario.text.toString()
                    Restaurante.correoElectronicoRestauranteAux = correoElectronico.text.toString()

                    val intent = Intent(this, DatosRestaurante::class.java)
                    crearUsuario(correoElectronico.text.toString(), contrasena.text.toString(), intent)
                }
            } else {
                val alerta = android.app.AlertDialog.Builder(this)
                alerta.setTitle("Error")
                alerta.setMessage("Llene todos los datos.")
                alerta.setPositiveButton("Aceptar", null)
                val dialog: android.app.AlertDialog = alerta.create()
                dialog.show()
            }
        }
    }

    fun crearUsuario (correo: String, contrasena: String, intent: Intent){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(
                correo,
                contrasena
            )
            .addOnCompleteListener {
                if(it.isSuccessful){
                    startActivity(intent)
                } else {
                    val alerta = AlertDialog.Builder(this)
                    alerta.setTitle("Error")
                    alerta.setMessage("Error al crear el usuario")
                    alerta.setPositiveButton("Aceptar", null)
                    val dialog: AlertDialog = alerta.create()
                    dialog.show()
                }
            }
    }

}