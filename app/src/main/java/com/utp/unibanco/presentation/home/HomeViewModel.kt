package com.utp.unibanco.presentation.home

import androidx.lifecycle.ViewModel
import com.utp.unibanco.data.repository.HomeRepositoryImpl
import com.utp.unibanco.domain.model.Transaction
import com.utp.unibanco.domain.usecase.GetHomeDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeState(
    val balance: String = "$ 0.00",
    val userName: String = "Usuario",
    val transactions: List<Transaction> = emptyList()
)

class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase = GetHomeDataUseCase(HomeRepositoryImpl())
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        _state.value = HomeState(
            balance = getHomeDataUseCase.getBalance(),
            userName = "Juan Pérez", // Esto podría venir de un UserUseCase más adelante
            transactions = getHomeDataUseCase.getTransactions()
        )
    }
}
