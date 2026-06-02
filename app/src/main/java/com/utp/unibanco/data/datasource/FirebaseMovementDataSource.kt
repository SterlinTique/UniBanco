package com.utp.unibanco.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.utp.unibanco.domain.model.Movement

class FirebaseMovementDataSource {

    private val database =
        FirebaseDatabase.getInstance().getReference("movements")

    fun getMovements(document: String): Task<DataSnapshot> {
        return database.child(document).get()
    }

    fun saveMovement(document: String, movement: Movement): Task<Void> {
        val key = database.child(document).push().key ?: return database.child(document).setValue(null)
        return database.child(document).child(key).setValue(movement)
    }

}

