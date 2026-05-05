package com.utp.unibanco.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var user = mutableStateOf("")
    var password = mutableStateOf("")

    fun onUserChange(newUser: String) {
        user.value = newUser
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun login(): Boolean {
        // Logica re simple pa autenticar
        return user.value.isNotEmpty() && password.value.isNotEmpty()
    }
}