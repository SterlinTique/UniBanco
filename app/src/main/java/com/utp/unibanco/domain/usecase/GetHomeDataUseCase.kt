package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.model.Transaction
import com.utp.unibanco.domain.repository.HomeRepository

class GetHomeDataUseCase(private val repository: HomeRepository) {
    fun getBalance(): String = repository.getBalance()
    fun getTransactions(): List<Transaction> = repository.getRecentTransactions()
}
