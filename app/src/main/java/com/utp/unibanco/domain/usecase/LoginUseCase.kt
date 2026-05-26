package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    operator fun invoke(document: String, password: String, onResult: (Boolean, Int) -> Unit) {
        repository.login(document, password, onResult)
    }
}