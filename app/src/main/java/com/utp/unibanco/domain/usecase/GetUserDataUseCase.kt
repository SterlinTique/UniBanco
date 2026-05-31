package com.utp.unibanco.domain.usecase

import com.utp.unibanco.data.repository.FirebaseAuthRepositoryImpl
import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.repository.AuthRepository

class GetUserDataUseCase(private val repository: AuthRepository = FirebaseAuthRepositoryImpl()) {
    operator fun invoke(document: String, onResult: (User?) -> Unit) {
        repository.getUser(document, onResult)
    }
}
