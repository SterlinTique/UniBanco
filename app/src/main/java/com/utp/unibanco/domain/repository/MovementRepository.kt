package com.utp.unibanco.domain.repository

import com.utp.unibanco.domain.model.Movement

interface MovementRepository {

    fun getMovements(document: String, onResult: (List<Movement>) -> Unit)
    fun saveMovement(document: String, movement: Movement, onResult: (Boolean) -> Unit
    )
}