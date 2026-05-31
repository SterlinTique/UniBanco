package com.utp.unibanco.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.utp.unibanco.domain.model.Account

class FirebaseAccountDataSource {

    private val database = FirebaseDatabase.getInstance().getReference("accounts")

    fun getAccount(document: String): Task<DataSnapshot> {
        return database.child(document).get()
    }

    fun saveAccount(document: String, account: Account): Task<Void> {
        return database.child(document).setValue(account)
    }

    fun updateBalance(document: String, newBalance: Double): Task<Void> {
        return database.child(document).child("balance").setValue(newBalance)
    }
}


