package com.gracegbe.proyecto_2b

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_restaurante)

        val nombreRestaurante = findViewById<TextView>(R.id.txtv_nombre_restaurante_reservacion)

        nombreRestaurante.text = Restaurante.nombreRestauranteSeteado

        val botonsalir = findViewById<ImageButton>(R.id.btn_salir_sesion_restaurante)

        botonsalir.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, InicioAplicacion::class.java)
            startActivity(intent)
        }

        val adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                Reservacion.listaReservacionesRestaurante
        )
        adaptador.notifyDataSetChanged()

        val listViewResercaciones = findViewById<ListView>(R.id.ltv_restaurante_reservaciones)

        recuperarReservacion(listViewResercaciones)
        registerForContextMenu(listViewResercaciones)

    }

    fun recuperarReservacion(listViewReservaciones: ListView){
        val db = Firebase.firestore

        Reservacion.listaReservacionesRestaurante.clear()

        Log.i("rest", "${intent.getStringExtra("usuarioRestaurante")}")

        db.collection("reservacion")
                .whereEqualTo("nombreUsuarioRestaurante", intent.getStringExtra("usuarioRestaurante"))
                .get()
                .addOnSuccessListener {
                    for (r in it){
                        val reservacion =
                                Reservacion(
                                        r.getString("nombreUsuarioCliente").toString(),
                                        r.getString("nombreUsuarioRestaurante").toString(),
                                        r.getString("fechaReservacion").toString(),
                                        r.getString("horaReservacion").toString(),
                                        r.getString("numeroPersonas").toString(),
                                        r.getString("estado").toString()
                                )
                        Reservacion.listaReservacionesRestaurante.add(reservacion)
                    }

                    //nombreRestaurante.text =

                    val adaptador = ArrayAdapter(
                            this,
                            android.R.layout.simple_list_item_1,
                            Reservacion.listaReservacionesRestaurante
                    )
                    adaptador.notifyDataSetChanged()
                    listViewReservaciones.adapter = adaptador
                }
    }

    override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menucontextualreservacion, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.opaceptar_reservacion -> {

                val nombreCliente =  Reservacion.listaReservacionesRestaurante[info.position].nombreUsuarioCliente
                val nombreRestaruante = Reservacion.listaReservacionesRestaurante[info.position].nombreUsuarioRestaurante
                val documento = "${nombreCliente}${nombreRestaruante}"

                val estado = hashMapOf<String, Any>(
                    "estado" to "aceptado"
                )

                val db = Firebase.firestore
                db.collection("reservacion")
                    .document(documento)
                    .update(estado)

                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("")
                alerta.setMessage("Estado cambiado correctmente")
                alerta.setPositiveButton("Aceptar", null)
                val dialog: AlertDialog = alerta.create()
                dialog.show()

                return true
            }
            R.id.op_cancelar_reservacion -> {

                val nombreCliente =  Reservacion.listaReservacionesRestaurante[info.position].nombreUsuarioCliente
                val nombreRestaruante = Reservacion.listaReservacionesRestaurante[info.position].nombreUsuarioRestaurante
                val documento = "${nombreCliente}${nombreRestaruante}"

                val estado = hashMapOf<String, Any>(
                    "estado" to "cancelado"
                )

                val db = Firebase.firestore
                db.collection("reservacion")
                    .document(documento)
                    .update(estado)

                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("")
                alerta.setMessage("Estado cambiado correctmente")
                alerta.setPositiveButton("Aceptar", null)
                val dialog: AlertDialog = alerta.create()
                dialog.show()

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

}

