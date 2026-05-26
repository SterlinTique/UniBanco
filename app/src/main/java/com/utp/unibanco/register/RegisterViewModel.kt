package com.utp.unibanco.register

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel : ViewModel() {

    private lateinit var database: DatabaseReference

    fun register(
        document: String,
        name: String,
        email: String,
        phone: String,
        birthDate: String,
        password: String,
        confirmPassword: String,
        onResult: (Boolean, String) -> Unit
    ) {

        database = FirebaseDatabase
            .getInstance()
            .getReference("users")

        // Validar campos vacíos
        if (
            document.isEmpty() ||
            name.isEmpty() ||
            email.isEmpty() ||
            phone.isEmpty() ||
            birthDate.isEmpty() ||
            password.isEmpty() ||
            confirmPassword.isEmpty()
        ) {

            onResult(false, "Completa todos los campos")
            return
        }

        // Validar contraseñas
        if (password != confirmPassword) {

            onResult(false, "Las contraseñas no coinciden")
            return
        }

        // Crear usuario
        val user = mapOf(
            "document" to document,
            "name" to name,
            "email" to email,
            "phone" to phone,
            "birthDate" to birthDate,
            "password" to password
        )

        // Guardar en Firebase
        database.child(document).setValue(user)

            .addOnSuccessListener {

                onResult(true, "Usuario registrado correctamente")
            }

            .addOnFailureListener {

                onResult(false, "Error al registrar usuario")
            }
    }
}