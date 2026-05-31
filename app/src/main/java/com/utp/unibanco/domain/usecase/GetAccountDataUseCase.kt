package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.model.Account
import com.utp.unibanco.domain.repository.AccountRepository

class GetAccountDataUseCase(private val repository: AccountRepository) {
    operator fun invoke(document: String, onResult: (Account?) -> Unit) {
        repository.getAccount(document, onResult)
    }
}