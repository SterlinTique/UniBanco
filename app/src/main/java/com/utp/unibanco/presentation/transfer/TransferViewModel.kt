package com.utp.unibanco.presentation.transfer

import androidx.lifecycle.ViewModel
import com.utp.unibanco.R
import com.utp.unibanco.domain.usecase.TransferMoneyUseCase
import com.utp.unibanco.data.repository.FirebaseAccountRepositoryImpl
import com.utp.unibanco.data.repository.FirebaseAuthRepositoryImpl
import com.utp.unibanco.data.repository.FirebaseMovementRepositoryImpl

class TransferViewModel(
    private val transferMoneyUseCase: TransferMoneyUseCase = TransferMoneyUseCase(
        accountRepository = FirebaseAccountRepositoryImpl(),
        authRepository = FirebaseAuthRepositoryImpl(),
        movementRepository = FirebaseMovementRepositoryImpl()
    )
) : ViewModel() {

    fun transfer(
        senderDocument: String,
        receiverInput: String,
        amount: String,
        onResult: (Boolean, Int) -> Unit
    ) {
        // Validar campos vacíos
        if (receiverInput.isBlank() || amount.isBlank()) {
            onResult(false, R.string.error_empty_fields)
            return
        }

        // Validar receptor: solo números y longitud 8–10
        if (!receiverInput.all { it.isDigit() }) {
            onResult(false, R.string.error_invalid_receiver)
            return
        }
        if (receiverInput.length !in 8..10) {
            onResult(false, R.string.error_receiver_length)
            return
        }

        // Validar monto
        val amountDouble = amount.toDoubleOrNull()
        if (amountDouble == null || amountDouble <= 0) {
            onResult(false, R.string.error_invalid_amount)
            return
        }

        // Ejecutar transferencia
        transferMoneyUseCase.execute(
            senderDocument = senderDocument,
            receiverIdentifier = receiverInput,
            amount = amountDouble
        ) { success, _ ->
            if (success) {
                onResult(true, R.string.success_transfer)
            } else {
                onResult(false, R.string.error_transfer)
            }
        }
    }
}
