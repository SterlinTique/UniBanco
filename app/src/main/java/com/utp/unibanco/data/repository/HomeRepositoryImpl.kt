package com.utp.unibanco.data.repository

import com.utp.unibanco.domain.model.Transaction
import com.utp.unibanco.domain.repository.HomeRepository

class HomeRepositoryImpl : HomeRepository {
    override fun getBalance(): String = "$ 1,250.00"

    override fun getRecentTransactions(): List<Transaction> = listOf(
        Transaction("1", "Supermercado", -45.50, "Hoy"),
        Transaction("2", "Pago de Luz", -120.00, "Ayer"),
        Transaction("3", "Transferencia recibida", 500.00, "25 May"),
        Transaction("4", "Restaurante", -32.00, "24 May")
    )
}
