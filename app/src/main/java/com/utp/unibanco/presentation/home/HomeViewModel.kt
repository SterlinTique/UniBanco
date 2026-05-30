package com.utp.unibanco.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.usecase.GetUserDataUseCase

class HomeViewModel(
    private val getUserDataUseCase: GetUserDataUseCase = GetUserDataUseCase()
) : ViewModel() {

    private val _userState = mutableStateOf<User?>(null)
    val userState: State<User?> = _userState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun loadUserData(document: String) {
        _isLoading.value = true
        getUserDataUseCase(document) { user ->
            _userState.value = user
            _isLoading.value = false
        }
    }
}
