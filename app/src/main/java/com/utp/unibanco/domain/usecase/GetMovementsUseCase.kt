package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.model.Movement
import com.utp.unibanco.domain.repository.MovementRepository

class GetMovementsUseCase(private val repository: MovementRepository) {
    operator fun invoke(document: String, onResult: (List<Movement>) -> Unit) {
        repository.getMovements(document, onResult)
    }
}