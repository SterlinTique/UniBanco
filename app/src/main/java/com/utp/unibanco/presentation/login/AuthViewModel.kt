package com.utp.unibanco.presentation.login

import androidx.lifecycle.ViewModel
import com.utp.unibanco.data.repository.FirebaseAuthRepositoryImpl
import com.utp.unibanco.domain.usecase.LoginUseCase
import com.utp.unibanco.R
class AuthViewModel(
    private val loginUseCase: LoginUseCase = LoginUseCase(FirebaseAuthRepositoryImpl())
) : ViewModel() {
    fun login(document: String, password: String, onResult: (Boolean, Int) -> Unit) {
        if (document.isBlank() || password.isBlank()) {
            onResult(false, R.string.error_empty_fields)
            return
        }
        loginUseCase(document, password, onResult)
    }
}
