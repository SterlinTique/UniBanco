package com.utp.unibanco.data.repository

import com.utp.unibanco.R
import com.utp.unibanco.data.datasource.FirebaseUserDataSource
import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.repository.AuthRepository

class FirebaseAuthRepositoryImpl(
    private val dataSource: FirebaseUserDataSource = FirebaseUserDataSource()
) : AuthRepository {

    override fun login(document: String, password: String, onResult: (Boolean, Int) -> Unit) {
        dataSource.getUser(document)
            .addOnSuccessListener { dataUser ->
                if (dataUser.exists()) {
                    val dbPassword = dataUser.child("password").value.toString()
                    if (dbPassword == password) {
                        onResult(true, R.string.success_login)
                    } else {
                        onResult(false, R.string.error_login)
                    }
                } else {
                    onResult(false, R.string.error_login)
                }
            }
            .addOnFailureListener {
                onResult(false, R.string.error_login)
            }
    }

    override fun register(user: User, onResult: (Boolean, Int) -> Unit) {
        dataSource.saveUser(user.document, user)
            .addOnSuccessListener {
                onResult(true, R.string.success_register)
            }
            .addOnFailureListener {
                onResult(false, R.string.error_register)
            }
    }

    override fun getUser(document: String, onResult: (User?) -> Unit) {
        dataSource.getUser(document)
            .addOnSuccessListener { dataUser ->
                if (dataUser.exists()) {
                    val user = dataUser.getValue(User::class.java)
                    onResult(user)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}
