package com.utp.unibanco.domain.repository

import com.utp.unibanco.domain.model.Account

interface AccountRepository {

    fun getAccount(
        document: String,
        onResult: (Account?) -> Unit
    )
}