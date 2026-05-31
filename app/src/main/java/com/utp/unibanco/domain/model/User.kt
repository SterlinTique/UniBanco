package com.utp.unibanco.domain.model

data class User(
    val document: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val birthDate: String = "",
    val password: String = "",
    val balance: Double = 0.0
)
