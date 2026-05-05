package com.utp.unibanco.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot

class AuthViewModel : ViewModel() {

    var document = mutableStateOf("")
    var password = mutableStateOf("")

    var loginState = mutableStateOf<Boolean?>(null) // null: sin intentar, true: exito, false: error
    var currentUserId = mutableStateOf<String?>(null) // document del usuario logueado, null si no hay
    private lateinit var database: DatabaseReference

    fun login(document: String, password: String) {

        database = FirebaseDatabase.getInstance().getReference("users")
        database.child(document).get().addOnSuccessListener { snapshot ->

            if (snapshot.exists()) {
                val dbPassword = snapshot.child("password").value.toString()

                if (dbPassword == password) {
                    currentUserId.value = document // Guardar el document del usuario logueado
                    loginState.value = true
                } else {
                    loginState.value = false
                }
            }

        }.addOnFailureListener {
            loginState.value = false
        }
    }

    fun onUserChange(newUser: String) {
        document.value = newUser
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }
}