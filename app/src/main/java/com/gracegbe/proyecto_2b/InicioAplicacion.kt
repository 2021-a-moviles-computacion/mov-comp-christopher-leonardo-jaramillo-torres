package com.gracegbe.proyecto_2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class InicioAplicacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_aplicacion)

        val nombreUsuario = findViewById<TextView>(R.id.txt_usuario)
        val contrasenaUsuario = findViewById<TextView>(R.id.txt_contrasena)
        val cliente = findViewById<RadioButton>(R.id.op_principal_cliente)
        val restaurante = findViewById<RadioButton>(R.id.op_principal_restaurante)

        val botonRegistrarse = findViewById<Button>(R.id.btn_registrarse)
        botonRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroPrincipal::class.java)
            startActivity(intent)
        }

        val ingresarCliente = findViewById<Button>(R.id.btn_ingresar)
        ingresarCliente.setOnClickListener {

            if (cliente.isChecked){
                Cliente.usuarioNombreClienteSeteado = nombreUsuario.text.toString()
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(nombreUsuario.text.toString(), contrasenaUsuario.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this, InicioCliente::class.java)
                            startActivity(intent)
                        } else {
                            val alerta = AlertDialog.Builder(this)
                            alerta.setTitle("Error")
                            alerta.setMessage("Error al ingresar")
                            alerta.setPositiveButton("Aceptar", null)
                            val dialog: AlertDialog = alerta.create()
                            dialog.show()
                        }
                    }
            } else if (restaurante.isChecked){
                Restaurante.nombreRestauranteSeteado = nombreUsuario.text.toString()

                Restaurante.listaRestaurante.clear()

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(nombreUsuario.text.toString(), contrasenaUsuario.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this, InicioRestaurante::class.java)
                            intent.putExtra("usuarioRestaurante", nombreUsuario.text.toString())
                            startActivity(intent)
                        } else {
                            val alerta = AlertDialog.Builder(this)
                            alerta.setTitle("Error")
                            alerta.setMessage("Error al ingresar")
                            alerta.setPositiveButton("Aceptar", null)
                            val dialog: AlertDialog = alerta.create()
                            dialog.show()
                        }
                    }
            } else {
                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("Error")
                alerta.setMessage("Llene todos los campos.")
                alerta.setPositiveButton("Aceptar", null)
                val dialog: AlertDialog = alerta.create()
                dialog.show()
            }
        }

    }

}