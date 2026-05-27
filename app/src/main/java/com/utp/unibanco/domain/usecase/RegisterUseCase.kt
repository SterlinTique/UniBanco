package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    operator fun invoke(user: User, onResult: (Boolean, Int) -> Unit) {
        repository.register(user, onResult)
    }
}