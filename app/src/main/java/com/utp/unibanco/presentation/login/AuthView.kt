package com.utp.unibanco.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.utp.unibanco.R
import com.utp.unibanco.presentation.components.ShowLoadingAlertDialog
import com.utp.unibanco.presentation.components.ShowMessageAlertDialog

@Composable
fun AuthView(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController
) {
    var document by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    var showLoadingAlert by remember { mutableStateOf(false) }
    var showMessageAlert by remember { mutableStateOf(false) }
    var titleDialog by remember { mutableIntStateOf(0) }
    var messageDialog by remember { mutableIntStateOf(0) }

    // Diálogo de carga
    if (showLoadingAlert) {
        ShowLoadingAlertDialog()
    }

    // Diálogo de mensaje
    if (showMessageAlert) {
        ShowMessageAlertDialog(
            onConfirmation = { showMessageAlert = false },
            dialogTitle = titleDialog,
            dialogText = messageDialog
        )
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
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Color(0xFF1353E8),
                        shape = MaterialTheme.shapes.medium
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

            // Título UniBanco
            Text(
                text = stringResource(R.string.login_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Subtítulo Bienvenido de vuelta
            Text(
                text = stringResource(R.string.login_subtitle),
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Documento
            OutlinedTextField(
                value = document,
                onValueChange = { document = it },
                label = { Text(stringResource(R.string.label_document), color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
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

            Spacer(modifier = Modifier.height(20.dp))

            // Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.label_password), color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
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

            Spacer(modifier = Modifier.height(10.dp))

            // Opciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF1353E8),
                            uncheckedColor = Color.Gray,
                            checkmarkColor = Color.White
                        )
                    )
                    Text(
                        text = stringResource(R.string.remember_me),
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }

                Text(
                    text = stringResource(R.string.forgot_password),
                    color = Color(0xFF1353E8),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón login
            Button(
                onClick = {
                    showLoadingAlert = true
                    viewModel.login(document, password) { success, messageResId ->
                        showLoadingAlert = false
                        if (success) {
                            navController.navigate("home")
                        } else {
                            titleDialog = R.string.error_login
                            messageDialog = messageResId
                            showMessageAlert = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1353E8)
                )
            ) {
                Text(
                    text = stringResource(R.string.btn_login),
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Registro
            Row {
                Text(
                    text = stringResource(R.string.text_no_account),
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                Text(
                    text = stringResource(R.string.text_register_here),
                    color = Color(0xFF1353E8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
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
fun AuthViewPreview() {
    val navController = rememberNavController()
    AuthView(navController = navController)
}
