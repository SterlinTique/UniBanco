package com.utp.unibanco.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.utp.unibanco.data.repository.FirebaseAccountRepositoryImpl
import com.utp.unibanco.data.repository.FirebaseAuthRepositoryImpl
import com.utp.unibanco.data.repository.FirebaseMovementRepositoryImpl
import com.utp.unibanco.domain.model.Account
import com.utp.unibanco.domain.model.Movement
import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.usecase.GetAccountDataUseCase
import com.utp.unibanco.domain.usecase.GetMovementsUseCase
import com.utp.unibanco.domain.usecase.GetUserDataUseCase

class HomeViewModel(
    private val getUserDataUseCase: GetUserDataUseCase = GetUserDataUseCase(FirebaseAuthRepositoryImpl()),
    private val getAccountDataUseCase: GetAccountDataUseCase = GetAccountDataUseCase(FirebaseAccountRepositoryImpl()),
    private val getMovementsUseCase: GetMovementsUseCase = GetMovementsUseCase(FirebaseMovementRepositoryImpl())
) : ViewModel() {

    private val _userState = mutableStateOf<User?>(null)
    val userState: State<User?> = _userState

    private val _accountState = mutableStateOf<Account?>(null)
    val accountState: State<Account?> = _accountState

    private val _movementsState = mutableStateOf<List<Movement>>(emptyList())
    val movementsState: State<List<Movement>> = _movementsState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun loadHomeData(document: String) {
        _isLoading.value = true

        getUserDataUseCase(document) { user ->
            _userState.value = user

            getAccountDataUseCase(document) { account ->
                _accountState.value = account

                getMovementsUseCase(document) { movements ->
                    _movementsState.value = movements
                    _isLoading.value = false
                }
            }
        }
    }
}
