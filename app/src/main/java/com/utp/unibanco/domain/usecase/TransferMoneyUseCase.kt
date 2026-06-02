package com.utp.unibanco.domain.usecase

import com.utp.unibanco.domain.repository.AccountRepository
import com.utp.unibanco.domain.repository.AuthRepository
import com.utp.unibanco.domain.repository.MovementRepository
import com.utp.unibanco.domain.model.Movement
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransferMoneyUseCase(
    private val accountRepository: AccountRepository,
    private val authRepository: AuthRepository,
    private val movementRepository: MovementRepository
) {

    fun execute(
        senderDocument: String,
        receiverIdentifier: String, // puede ser doc o teléfono
        amount: Double,
        onResult: (Boolean, String) -> Unit
    ) {

        // 1. validar monto
        if (amount <= 0) {
            onResult(false, "Monto inválido")
            return
        }

        // 2. obtener usuario destino (primero intentamos documento)
        authRepository.getUser(receiverIdentifier) { userByDoc ->
            if (userByDoc != null) {
                processTransfer(senderDocument, userByDoc.document, amount, onResult)
            } else {
                // 3. si no existe por documento, buscar por teléfono
                authRepository.getUserByPhone(receiverIdentifier) { userByPhone ->
                    if (userByPhone != null) {
                        processTransfer(senderDocument, userByPhone.document, amount, onResult)
                    } else {
                        onResult(false, "Usuario destino no encontrado")
                    }
                }
            }
        }
    }

    private fun processTransfer(
        sender: String,
        receiver: String,
        amount: Double,
        onResult: (Boolean, String) -> Unit
    ) {

        // 4. obtener cuenta origen
        accountRepository.getAccount(sender) { senderAccount ->

            if (senderAccount == null) { onResult(false, "Cuenta origen no existe")
                return@getAccount
            }
            if (senderAccount.balance < amount) { onResult(false, "Saldo insuficiente")
                return@getAccount
            }
            // 5. obtener cuenta destino
            accountRepository.getAccount(receiver) { receiverAccount ->
                if (receiverAccount == null) { onResult(false, "Cuenta destino no existe")
                    return@getAccount
                }

                val newSenderBalance = senderAccount.balance - amount
                val newReceiverBalance = receiverAccount.balance + amount

                // 6. actualizar cuentas
                accountRepository.updateBalance(sender, newSenderBalance) { ok1 ->
                    if (!ok1) { onResult(false, "Error actualizando origen")
                        return@updateBalance
                    }
                    accountRepository.updateBalance(receiver, newReceiverBalance) { ok2 ->
                        if (!ok2) { onResult(false, "Error actualizando destino")
                            return@updateBalance
                        }

                        // 7. crear movimientos
                        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            .format(Date())

                        val outMovement = Movement(amount = amount, date = date, type = "transferencia enviada")
                        val inMovement = Movement(amount = amount, date = date, type = "transferencia recibida")

                        movementRepository.saveMovement(sender, outMovement) { _ ->
                            movementRepository.saveMovement(receiver, inMovement) { _ ->
                                onResult(true, "Transferencia exitosa")
                            }
                        }
                    }
                }
            }
        }
    }
}