package com.utp.unibanco.presentation.transfer

import androidx.lifecycle.ViewModel
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
        onResult: (Boolean, String) -> Unit
    ) {
        if (receiverInput.isBlank() || amount.isBlank()) { onResult(false, "Campos vacíos")
            return
        }
        val amountDouble = amount.toDoubleOrNull()

        if (amountDouble == null || amountDouble <= 0) { onResult(false, "Monto inválido")
            return
        }

        transferMoneyUseCase.execute(senderDocument = senderDocument, receiverIdentifier = receiverInput, amount = amountDouble, onResult = onResult)
    }
}