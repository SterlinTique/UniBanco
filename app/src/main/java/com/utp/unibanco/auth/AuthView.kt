package com.utp.unibanco.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val user = viewModel.user.value
    val password = viewModel.password.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )

        Text("Usuario", color = Color.Gray)
        BasicTextField(
            value = user,
            onValueChange = { viewModel.onUserChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2A2928))
                .padding(12.dp),
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
                // aqui tin ViewModel o lo que sea para validar el login
                // o tan navigation a home directamente
                if (viewModel.login()) {
                    // navegación simple
                    navController.navigate("home")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA70E)
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
    }
}

@Preview
@Composable
fun TestAuthView() {
    val navController = rememberNavController()
    AuthView(navController)
}