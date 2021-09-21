package com.gracegbe.firebase01

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {

    var ultimoDocuento: DocumentSnapshot? = null
    var query: Query? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_restaurante)

        val botonCrear = findViewById<Button>(R.id.btn_crear_restaurante)

        val botonDatosPrueba = findViewById<Button>(R.id.btn_datos_prueba)
        botonDatosPrueba.setOnClickListener {
            //crearDatosPrueba()
            transaccion()
        }

        val botonConsultar = findViewById<Button>(R.id.btn_consultar)
        botonConsultar.setOnClickListener {
            consultar()
        }


        botonCrear.setOnClickListener {
            crearRestaurante()
        }
    }

    fun crearRestaurante(){
        val editTextNombre = findViewById<EditText>(R.id.et_nombre_restaurante)
        val nuevoRestaurante = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
        )

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")

        referencia
            .add(nuevoRestaurante)
            .addOnSuccessListener {
                editTextNombre.text.clear()
            }
            .addOnFailureListener{}

    }

    fun crearDatosPrueba(){
        val db = Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)

    }

    fun transaccion(){
        val db = Firebase.firestore
        val referenciaCities = db.collection("cities").document("SF")

        db.runTransaction{
            transaction ->
            val documentoAactual = transaction.get(referenciaCities)
            val poblacion = documentoAactual.getDouble("population")

            if(poblacion != null){
                val  nuevaPoblacion = poblacion +1
                transaction.update(referenciaCities, "population", nuevaPoblacion)
            }
        }
            .addOnSuccessListener {
                Log.i("transaccion","transaccion completada")
            }
            .addOnFailureListener{
                Log.i("transaccion","error")
            }


    }

    fun guardarQuery(documentSnapshot: QuerySnapshot, refCities: Query){
        if (documentSnapshot.size() > 0){
            val ultimoDocumento = documentSnapshot.documents[documentSnapshot.size() - 1]
            query = refCities
                .startAfter(ultimoDocumento)
        }
    }

    fun consultar(){
        val db = Firebase.firestore

        val refCities = db.collection("cities")
            .orderBy("population")
            .limit(2)

        var tarea: Task<QuerySnapshot>? = null
        if(query == null){
            tarea = refCities.get()
        } else {
            tarea = query!!.get()
        }

        if (tarea != null){
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, refCities)

                    for(ciudad in documentSnapshots){
                        Log.i("consultas", "${ciudad.data}")
                    }

                }
                .addOnFailureListener{
                    Log.i("consultas", "ERROR: ${it}")
                }
        }



        /*var citiesRef = db
            .collection("cities")
            .orderBy("name")
            .limit(2)

        if(ultimoDocuento != null){
            citiesRef = citiesRef.startAt(ultimoDocuento)
            citiesRef = db
                .collection("cities")
                .orderBy("name")
                .limit(2)
        }

        // Paginacion
        citiesRef
            .get()
            .addOnSuccessListener {
                if(it.size() > 0) {
                    ultimoDocuento = it.documents[it.size() - 1]
                }else {
                    for (ciudad in it) {
                        Log.i("consultas", "${ciudad.data}")
                    }
                }

            }
            .addOnFailureListener{}*/

        /*citiesRef
            .document("BJ") //ID
            .get()
            .addOnSuccessListener {
                Log.i("consultas","${it.data}")
            }
            .addOnFailureListener{}

        citiesRef
            .whereEqualTo("country", "China")
            .get()
            .addOnSuccessListener {
                Log.i("consultas","${it.documents}")

                for (ciudad in it){
                    Log.i("consultas","${ciudad.data}")
                    Log.i("consultas","${ciudad.id}")
                }

            }
            .addOnFailureListener{}

        citiesRef
            .whereEqualTo("capital", false)
            .whereArrayContainsAny("regions", arrayListOf("socal", "norcal"))
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas","== array-contains ${ciudad.data}")
                }
            }

        // buscar por dos o mas elementos con == >=
        citiesRef
            .whereEqualTo("capital", false)
            .whereGreaterThanOrEqualTo("population", 1000000)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas","== array-contains ${ciudad.data}")
                }
            }

        // Se necesita un nuevo indice para cambiar el otrden
        citiesRef
            .whereEqualTo("capital", false)
            . whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas","== array-contains ${ciudad.data}")
                }
            }*/

    }



}