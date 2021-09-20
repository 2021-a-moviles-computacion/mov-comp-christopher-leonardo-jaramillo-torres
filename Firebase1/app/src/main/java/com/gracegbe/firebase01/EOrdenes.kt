package com.gracegbe.firebase01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.security.auth.login.LoginException

class EOrdenes : AppCompatActivity() {

    lateinit var productos: MutableList<DocumentSnapshot>
    lateinit var restaurante: MutableList<DocumentSnapshot>
    var productosR = ArrayList<Producto>()
    var restauranteR = ArrayList<Restaurante>()
    var ordenesR = ArrayList<Orden>()
    var suma = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_ordenes)

        val spinerProducto = findViewById<Spinner>(R.id.sp_producto)
        val spinerRestaurante = findViewById<Spinner>(R.id.sp_restaurantes)

        recuperarProductos(spinerProducto)
        recuperarRestaurante(spinerRestaurante)

        val añadirLista = findViewById<Button>(R.id.btn_anadir_lista_producto)
        val cantidad = findViewById<TextView>(R.id.et_cantidad_producto)
        val total = findViewById<TextView>(R.id.txt_final)


        añadirLista.setOnClickListener {
            añadirALista(spinerProducto, spinerRestaurante, cantidad.text.toString().toInt(), ordenesR)

            cantidad.text = ""

            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ordenesR)

            val listaOrdenes = findViewById<ListView>(R.id.lv_lista_productos)

            listaOrdenes.adapter = adaptador

            adaptador.notifyDataSetChanged()

            total.text = suma.toString()

        }

    }

    fun recuperarProductos(spinerProducto: Spinner){

        val db = Firebase.firestore

        val referencia = db.collection("producto").get()
            .addOnSuccessListener {
                productos = it.documents
                productos.forEach {
                    iteracion ->
                        productosR.add(Producto(iteracion.get("nombre").toString(), iteracion.get("precio").toString().toDouble()))
                        //productosR.add(iteracion.get("nombre").toString())
                }
                val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productosR)
                spinerProducto.adapter = adaptador
            }

    }

    fun recuperarRestaurante(spinerRestaurante: Spinner){

        val db = Firebase.firestore

        val referencia = db.collection("restaurante").get()
            .addOnSuccessListener {
                restaurante = it.documents
                restaurante.forEach {
                        iteracion ->
                    restauranteR.add(Restaurante(iteracion.get("nombre").toString()))
                }
                val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, restauranteR)
                spinerRestaurante.adapter = adaptador

            }
    }

    fun añadirALista(spinerProducto: Spinner, spinerRestaurante: Spinner, cantidad: Int, orden: ArrayList<Orden>){

        val letras = spinerProducto.selectedItem.toString().indexOf(" -")
        val nombre = spinerProducto.selectedItem.toString().substring(0, letras)
        //val precioT = 0.0

        productosR.forEach {
            if(it.nombre.toString() == nombre){
                Log.i("if","entro al if")
                suma += it.precio * cantidad
                //precioT = it.precio
                orden.add(Orden(spinerRestaurante.selectedItem.toString(), spinerProducto.selectedItem.toString(), cantidad, cantidad*it.precio))
                Log.i("if","entro al if")
            }
        }



    }


}
