package com.gracegbe.proyecto_2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class InicioCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_cliente)

        val botonNotificacion = findViewById<ImageButton>(R.id.btn_ir_notificaciones)

        botonNotificacion.setOnClickListener{
            recuperarNotificaciones()
        }


        val botonsalir = findViewById<ImageButton>(R.id.btn_salir_sesion_cliente)

        botonsalir.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, InicioAplicacion::class.java)
            startActivity(intent)
        }

        val recyclerRestaurantes = findViewById<RecyclerView>(R.id.rcv_inicio_clientes_restaurantes)

        recuperarRestaurante()
        iniciarRecyclerRestaurante(
            Restaurante.listaRestaurante,
            this,
            recyclerRestaurantes
        )

    }

    fun recuperarNotificaciones(){
        val db = Firebase.firestore
        Reservacion.listaReservacionesCliente.clear()

        Log.i("cliente", "cliente seteado ${Cliente.usuarioNombreClienteSeteado}")

        db.collection("reservacion")
            .whereEqualTo("nombreUsuarioCliente", Cliente.usuarioNombreClienteSeteado)
            .get()
            .addOnSuccessListener { it ->
                for (r in it){
                    val reservacion = Reservacion(
                        r.getString("nombreUsuarioCliente").toString(),
                        r.getString("nombreUsuarioRestaurante").toString(),
                        r.getString("fechaReservacion").toString(),
                        r.getString("horaReservacion").toString(),
                        r.getString("numeroPersonas").toString(),
                        r.getString("estado").toString()
                    )
                    //Log.i("cliente", "cliente recuperado ${reservacion.nombreUsuarioCliente}")
                    Reservacion.listaReservacionesCliente.add(reservacion)

                    val alerta = AlertDialog.Builder(this)
                    val mensajeAux = "${reservacion.nombreUsuarioRestaurante} - ${reservacion.fechaReservacion} - ${reservacion.horaReservacion} - ${reservacion.estado}"
                    alerta.setTitle("Restaurante - Hora - Fecha - Estado")
                    alerta.setMessage(mensajeAux)
                    alerta.setPositiveButton("Aceptar", null)
                    val dialog: AlertDialog = alerta.create()
                    dialog.show()

                }
            }
    }

    fun recuperarRestaurante(){

        val db = Firebase.firestore
        Restaurante.listaRestaurante.clear()

        db.collection("restaurante")
            .get()
            .addOnSuccessListener {
                for (restaurante in it){
                    val nuevoRestaurante = Restaurante(
                        restaurante.id.toString(),
                        restaurante.getString("correoElectronicoRestaurante").toString(),
                        restaurante.getString("nombreRestaurante").toString(),
                        restaurante.getString("horarioAtencion").toString(),
                        restaurante.getString("numeroCelular").toString(),
                        restaurante.getString("direccionRestaurante").toString(),
                        restaurante.getString("descripcionRestaurante").toString()
                    )
                    Log.i("cliente","nombre restaurante aux ${Restaurante.nombreRestaurenteAux.toString()}")
                    Restaurante.listaRestaurante.add(nuevoRestaurante)
                }
            }
    }

    fun iniciarRecyclerRestaurante(
        listaRestaurante: List<Restaurante>,
        actividad: InicioCliente,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerVistaRestaurante(
            actividad,
            listaRestaurante,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }


}