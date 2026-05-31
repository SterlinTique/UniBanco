package com.utp.unibanco.data.repository

import com.utp.unibanco.data.datasource.FirebaseMovementDataSource
import com.utp.unibanco.domain.model.Movement
import com.utp.unibanco.domain.repository.MovementRepository

class FirebaseMovementRepositoryImpl(
    private val dataSource: FirebaseMovementDataSource = FirebaseMovementDataSource()
) : MovementRepository {

    override fun getMovements(document: String, onResult: (List<Movement>) -> Unit) {
        dataSource.getMovements(document)
            .addOnSuccessListener { dataSnapshot ->
                val movements = mutableListOf<Movement>()
                for (movementSnapshot in dataSnapshot.children) {
                    val movement = movementSnapshot.getValue(Movement::class.java)
                    movement?.let {
                        movements.add(it)
                    }
                }
                onResult(movements)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    override fun saveMovement(document: String, movement: Movement, onResult: (Boolean) -> Unit) {
        dataSource.saveMovement(document, movement)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}