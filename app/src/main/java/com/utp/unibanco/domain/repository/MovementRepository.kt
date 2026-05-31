package com.utp.unibanco.domain.repository

import com.utp.unibanco.domain.model.Movement

interface MovementRepository {

    fun getMovements(
        document: String,
        onResult: (List<Movement>) -> Unit
    )
}