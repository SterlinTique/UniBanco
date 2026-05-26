package com.utp.unibanco.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegisterView(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {

    var document by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "UniBanco",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1353E8)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Crear cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Completa tus datos",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Documento
        OutlinedTextField(
            value = document,
            onValueChange = { document = it },

            label = {
                Text("Documento")
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color(0xFF1353E8)
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Nombre
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },

            label = {
                Text("Nombre completo")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Correo
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },

            label = {
                Text("Correo electrónico")
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Teléfono
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },

            label = {
                Text("Teléfono")
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Fecha de nacimiento
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },

            label = {
                Text("Fecha de nacimiento")
            },

            placeholder = {
                Text("DD/MM/AAAA")
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },

            label = {
                Text("Contraseña")
            },

            visualTransformation = PasswordVisualTransformation(),

            modifier = Modifier.fillMaxWidth(),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },

            label = {
                Text("Confirmar contraseña")
            },

            visualTransformation = PasswordVisualTransformation(),

            modifier = Modifier.fillMaxWidth(),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                viewModel.register(
                    document = document,
                    name = name,
                    email = email,
                    phone = phone,
                    birthDate = birthDate,
                    password = password,
                    confirmPassword = confirmPassword

                ) { success, response ->

                    message = response

                    if (success) {

                        navController.navigate("auth")
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1353E8)
            )
        ) {

            Text(
                text = "Registrarse",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (message.isNotEmpty()) {

            Text(
                text = message,

                color = if (
                    message.contains("correctamente")
                ) {
                    Color(0xFF2E7D32)
                } else {
                    Color.Red
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {

            Text(
                text = "¿Ya tienes una cuenta? ",
                color = Color.Gray,
                fontSize = 13.sp
            )

            Text(
                text = "Inicia sesión",
                color = Color(0xFF1353E8),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .padding(start = 2.dp)
                    .clickable() {
                        navController.navigate("auth")
                    }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TestRegisterView() {

    val navController = rememberNavController()

    RegisterView(navController)
}