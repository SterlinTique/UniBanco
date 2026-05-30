package com.utp.unibanco.domain.repository

import com.utp.unibanco.domain.model.Transaction

interface HomeRepository {
    fun getBalance(): String
    fun getRecentTransactions(): List<Transaction>
}
