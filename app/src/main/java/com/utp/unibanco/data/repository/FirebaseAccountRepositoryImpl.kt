package com.utp.unibanco.data.repository

import com.utp.unibanco.data.datasource.FirebaseAccountDataSource
import com.utp.unibanco.domain.model.Account
import com.utp.unibanco.domain.repository.AccountRepository

class FirebaseAccountRepositoryImpl(
    private val dataSource: FirebaseAccountDataSource = FirebaseAccountDataSource()
) : AccountRepository {

    override fun getAccount(document: String, onResult: (Account?) -> Unit) {
        dataSource.getAccount(document)
            .addOnSuccessListener { dataAccount ->
                if (dataAccount.exists()) {
                    val account =
                        dataAccount.getValue(Account::class.java)
                    onResult(account)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    override fun updateBalance(document: String, newBalance: Double, onResult: (Boolean) -> Unit) {
        dataSource.updateBalance(document, newBalance)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}