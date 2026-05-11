package com.utp.unibanco.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
fun AuthView(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    val document = viewModel.document.value
    val password = viewModel.password.value

    val loginState = viewModel.loginState.value

    var rememberMe by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(loginState) {
        if (loginState == true) {
            navController.navigate("home")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .align(Alignment.Center)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(25.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        Color(0xFF1353E8),
                        shape = RoundedCornerShape(15.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "UB",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "UniBanco",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Bienvenido de vuelta",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Documento
            Text(
                text = "Documento de identidad",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 13.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                value = document,
                onValueChange = {
                    viewModel.onUserChange(it)
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        Color.LightGray,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(15.dp),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                singleLine = true,

                decorationBox = { innerTextField ->

                    if (document.isEmpty()) {
                        Text(
                            text = "Ingresa tu documento",
                            color = Color.Gray
                        )
                    }

                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Contraseña
            Text(
                text = "Contraseña",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 13.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                value = password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },

                visualTransformation = PasswordVisualTransformation(),

                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        Color.LightGray,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(15.dp),

                singleLine = true,

                decorationBox = { innerTextField ->

                    if (password.isEmpty()) {
                        Text(
                            text = "Ingresa tu contraseña",
                            color = Color.Gray
                        )
                    }

                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Opciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = {
                            rememberMe = it
                        }
                    )

                    Text(
                        text = "Recordarme",
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color(0xFF1353E8),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón login
            Button(
                onClick = {
                    viewModel.login(document, password)
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(14.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1353E8)
                )

            ) {

                Text(
                    text = "Iniciar sesión",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {

                Text(
                    text = "¿No tienes una cuenta? ",
                    color = Color.Gray,
                    fontSize = 13.sp
                )

                Text(
                    text = "Regístrate aquí",
                    color = Color(0xFF1353E8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (loginState == false) {

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Credenciales incorrectas",
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "© 2026 UniBanco. Todos los derechos reservados.",
                color = Color.Gray,
                fontSize = 11.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestAuthView() {

    val navController = rememberNavController()

    AuthView(navController)
}