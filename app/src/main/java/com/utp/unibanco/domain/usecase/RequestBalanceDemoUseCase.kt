package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.model.Account
import com.utp.unibanco.domain.repository.AccountRepository

class RequestBalanceDemoUseCase(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(document: String, amountToRequest: Double, onResult: (Boolean) -> Unit) {
        accountRepository.getAccount(document) { account ->
            if (account != null) {
                val currentBalance = account.balance ?: 0.0
                val newBalance = currentBalance + amountToRequest
                accountRepository.updateBalance(document, newBalance) { success ->
                    onResult(success)
                }
            } else {
                val newAccount = Account(userId = document, balance = amountToRequest, type = "ahorros")
                accountRepository.saveAccount(document, newAccount) { success ->
                    onResult(success)
                }
            }
        }
    }
}