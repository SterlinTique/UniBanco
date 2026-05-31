package com.utp.unibanco.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.utp.unibanco.domain.model.User

class FirebaseUserDataSource {

    private val database = FirebaseDatabase.getInstance().getReference("users")

    fun getUser(document: String): Task<DataSnapshot> {
        return database.child(document).get()
    }

    fun saveUser(document: String, user: User): Task<Void> {
        return database.child(document).setValue(user)
    }

    fun getUserByPhone(phone: String): Query { return database
            .orderByChild("phone")
            .equalTo(phone)
    }
}