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

        if (!document.all { it.isDigit() }) {
            onResult(false, R.string.error_invalid_document)
            return
        }
        if (document.length < 8) {
            onResult(false, R.string.error_document_length)
            return
        }
        if (password.length < 6) {
            onResult(false, R.string.error_password_length)
            return
        }

        loginUseCase(document, password, onResult)
    }
}
