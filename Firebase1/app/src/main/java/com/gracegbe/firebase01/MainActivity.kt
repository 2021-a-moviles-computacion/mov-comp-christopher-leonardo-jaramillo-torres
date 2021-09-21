package com.gracegbe.firebase01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gracegbe.firebase01.dbo.FireStoreUsuarioDto
import java.util.Calendar.getInstance

class MainActivity : AppCompatActivity() {

    val CODIGO_INICIO_SESION = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val botoonLogin = findViewById<Button>(R.id.btn_login)
        botoonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

        val botonLogout = findViewById<Button>(R.id.btn_logout)
        botonLogout.setOnClickListener {
            solicitarSalirDelAplicactivo()
        }

        val botonProducto = findViewById<Button>(R.id.btn_producto)
        botonProducto.setOnClickListener {
            val intent = Intent(this, CProducto::class.java)
            startActivity(intent)
        }

        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        botonRestaurante.setOnClickListener {
            val intent = Intent(this, DRestaurante::class.java)
            startActivity(intent)
        }

        val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)
        botonOrdenes.setOnClickListener {
            val intent = Intent(this, EOrdenes::class.java)
            startActivity(intent)
        }

        val botonIrapa = findViewById<Button>(R.id.btn_ir_mapa)
        botonIrapa.setOnClickListener {
            val intent = Intent(this, FMapsActivity::class.java)
            startActivity(intent)
        }

    }
    
    fun llamarLoginUsuario(){
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTosAndPrivacyPolicyUrls(
                        "https://example.com/terms.html",
                        "https://example.com/privacy.html").build(),CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_INICIO_SESION ->{
                if(resultCode == Activity.RESULT_OK){
                    val usuario = IdpResponse.fromResultIntent(data)
                    if(usuario?.isNewUser == true){
                        Log.i("firebase","Nuevo Usuario")
                        registrarUsuarioPrimeraVez(usuario)
                        //registrarPorPrimeraVez(usuario)
                    }else{
                        setaerUsuarioFirebase()
                        Log.i("firebase","Antiguo Usuario")
                    }
                }else {
                    Log.i("firebase","El usuario cancelo")
                }
            }
        }
    }

    fun registrarUsuarioPrimeraVez(usuario: IdpResponse){

        val usuarioLogeado = FirebaseAuth.getInstance().getCurrentUser()

        val identificadorUsuario = usuario.email

        if (usuario.email != null && usuarioLogeado != null){

            // roles: guarda roles de usuario {usuario, administrador}

            val db = Firebase.firestore

            val rolesUsuario = arrayListOf("usuario")
            val nueboUsuario = hashMapOf<String, Any>("roles" to rolesUsuario, "uid" to usuarioLogeado.uid, "email" to identificadorUsuario.toString())

            val identificadorUsuario = usuario.email

            db.collection("usuario")

                    // con esto el identificar es el correo que se ingrea, tambien se puede dejar que el firestore cree un identificador para cada usario.
                    .document(identificadorUsuario.toString())
                    .set(nueboUsuario)
                    //.add(nueboUsuario)
                    .addOnSuccessListener {
                    Log.i("Firestore", "Se creo")
                        setaerUsuarioFirebase()
                    }.addOnFailureListener {
                    Log.i("Firestore", "Fallo")
                    }
        }else{
            Log.i("firebase", "Error")
        }
    }


    /*fun registrarPorPrimeraVez(usuario: IdpResponse){

        val usuarioLogueado = FirebaseAuth.getInstance()
            .currentUser

        if(usuario.email != null && usuarioLogueado != null){
            val db = Firebase.firestore
            val rolesUsuario = arrayListOf("usuario")
            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to rolesUsuario,
                "uid" to usuarioLogueado.uid
            )

            val identificarUsuario = usuario.email

            db.collection("usuario")
                .document(identificarUsuario.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firestore", "Usuario creado")
                }
                .addOnFailureListener{
                    Log.i("firestore", "Fallo al crear")
                }

        }else{
            Log.i("firebase", "Error")
        }
    }*/

    fun setearBienvenida(){
        val textViewBienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonLogout = findViewById<Button>(R.id.btn_logout)
        val botonProducto = findViewById<Button>(R.id.btn_producto)
        val botonDRestaurante = findViewById<Button>(R.id.btn_restaurante)
        val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)

        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonDRestaurante.visibility =  View.VISIBLE
            botonOrdenes.visibility =  View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingresa al aplicativo"
            botonLogin.visibility = View.VISIBLE
            botonLogout.visibility = View.INVISIBLE
            botonProducto.visibility = View.INVISIBLE
            botonDRestaurante.visibility =  View.INVISIBLE
            botonOrdenes.visibility =  View.INVISIBLE
        }
    }

    fun solicitarSalirDelAplicactivo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnSuccessListener {
                BAuthUsuario.usuario = null
            }

        setearBienvenida()
    }

    fun setaerUsuarioFirebase(){

        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser

        if(usuarioLocal != null){

            val db = Firebase.firestore

            val referencia = db
                .collection("usuario")
                .document(usuarioLocal.email.toString())

            referencia
                .get()
                .addOnSuccessListener {
                    val usuarioCargado: FireStoreUsuarioDto? = it.toObject(FireStoreUsuarioDto::class.java)

                    if (usuarioCargado != null){
                        BAuthUsuario.usuario = BUsuarioFirebase(
                            usuarioCargado.uid,
                            usuarioCargado.email,
                            usuarioCargado.roles
                        )
                        setearBienvenida()
                    }

                    //BAuthUsuario.usuario = usuarioCargado
                    Log.i("Firestore", "Se creo")
                }.addOnFailureListener {
                    Log.i("Firestore", "Fallo")
                }
        }

    }

}