package com.utp.unibanco.presentation.register

import androidx.lifecycle.ViewModel
import com.utp.unibanco.R
import com.utp.unibanco.data.repository.FirebaseAuthRepositoryImpl
import com.utp.unibanco.domain.model.User
import com.utp.unibanco.domain.usecase.RegisterUseCase
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase =
        RegisterUseCase(FirebaseAuthRepositoryImpl())
) : ViewModel() {

    fun register(
        document: String,
        name: String,
        email: String,
        phone: String,
        birthDate: String,
        password: String,
        confirmPassword: String,
        onResult: (Boolean, Int) -> Unit
    ) {
        // Validar campos vacios
        if (document.isBlank() || name.isBlank() || email.isBlank() ||
            phone.isBlank() || birthDate.isBlank() || password.isBlank()
        ) {
            onResult(false, R.string.error_empty_fields)
            return
        }

        // Documento: solo numeros y longitud 8–10
        if (!document.all { it.isDigit() }) {
            onResult(false, R.string.error_invalid_document)
            return
        }
        if (document.length !in 8..10) {
            onResult(false, R.string.error_document_length)
            return
        }

        // Nombre: solo letras y espacios
        if (!name.all { it.isLetter() || it.isWhitespace() }) {
            onResult(false, R.string.error_invalid_name)
            return
        }

        // Correo: formato valido
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onResult(false, R.string.error_invalid_email)
            return
        }

        // Telefono: solo numeros y longitud exacta 10
        if (!phone.all { it.isDigit() }) {
            onResult(false, R.string.error_invalid_phone)
            return
        }
        if (phone.length != 10) {
            onResult(false, R.string.error_phone_length)
            return
        }

        // Fecha de nacimiento: formato y reglas de edad
        val birthDateError = validateBirthDate(birthDate)
        if (birthDateError != null) {
            onResult(false, birthDateError)
            return
        }

        // Contraseña: longitud mínima
        if (password.length < 6) {
            onResult(false, R.string.error_password_length)
            return
        }

        // Confirmar contraseña
        if (password != confirmPassword) {
            onResult(false, R.string.error_password_match)
            return
        }

        val user = User(document, name, email, phone, birthDate, password)
        registerUseCase(user, onResult)
    }

    // Función auxiliar para validar fecha de nacimiento
    private fun validateBirthDate(birthDate: String): Int? {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = sdf.parse(birthDate) ?: return R.string.error_invalid_birthdate

        val today = Calendar.getInstance()
        val birth = Calendar.getInstance().apply { time = date }

        // No permitir fechas futuras
        if (birth.after(today)) return R.string.error_future_birthdate

        // Calcular edad
        var age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) age--

        if (age < 14) return R.string.error_min_age
        if (age > 100) return R.string.error_max_age

        return null
    }
}
