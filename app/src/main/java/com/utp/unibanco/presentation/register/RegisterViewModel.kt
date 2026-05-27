package com.utp.unibanco.presentation.register

import androidx.lifecycle.ViewModel
import com.utp.unibanco.R
import com.utp.unibanco.data.repository.FirebaseAuthRepositoryImpl
import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.usecase.RegisterUseCase

class RegisterViewModel(

    private val registerUseCase: RegisterUseCase =
        RegisterUseCase(FirebaseAuthRepositoryImpl())
) : ViewModel() {

    fun register(
        document: String,
        name: String,
        email: String,
        phone: String,
        birthDate: String,
        password: String,
        confirmPassword: String,
        onResult: (Boolean, Int) -> Unit
    ) {

        // Validar campos vacíos
        if (
            document.isBlank() ||
            name.isBlank() ||
            email.isBlank() ||
            phone.isBlank() ||
            birthDate.isBlank() ||
            password.isBlank()
        ) {
            onResult(false, R.string.error_empty_fields)
            return
        }

        // Validar contraseñas
        if (password != confirmPassword) {
            onResult(false, R.string.error_password_match)
            return
        }

        val user = User(
            document = document,
            name = name,
            email = email,
            phone = phone,
            birthDate = birthDate,
            password = password
        )

        registerUseCase(user, onResult)
    }
}