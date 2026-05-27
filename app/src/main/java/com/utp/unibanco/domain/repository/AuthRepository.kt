package com.utp.unibanco.domain.repository

import  com.utp.unibanco.domain.model.User

interface AuthRepository {
    fun login(document: String, password: String, onResult: (Boolean, Int) -> Unit)
    fun register(user: User, onResult: (Boolean, Int) -> Unit)
}