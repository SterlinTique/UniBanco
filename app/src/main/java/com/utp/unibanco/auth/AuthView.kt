package com.utp.unibanco.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthView(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    val document = viewModel.document.value
    val password = viewModel.password.value

    val loginState = viewModel.loginState.value

    LaunchedEffect(loginState) {
        if (loginState == true) {
            navController.navigate("home")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Inicio de sesión",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )

        Text("Documento", color = Color.Gray)
        BasicTextField(
            value = document,
            onValueChange = { viewModel.onUserChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2A2928))
                .padding(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text("Contraseña", color = Color.Gray)
        BasicTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2A2928))
                .padding(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                viewModel.login(document, password)
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF435BD5)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ingresar",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Text(
            text = "Registrarse",
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 10.dp)
        )

        if (loginState == false) {
            Text(
                text = "Credenciales incorrectas",
                color = Color.Red,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun TestAuthView() {
    val navController = rememberNavController()
    AuthView(navController)
}