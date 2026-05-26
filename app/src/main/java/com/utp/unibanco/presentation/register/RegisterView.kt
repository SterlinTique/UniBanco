package com.utp.unibanco.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
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

@Composable
fun RegisterView(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {

    var document by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var message by remember {
        mutableIntStateOf(0)
    }

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
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1353E8)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.register_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(R.string.register_subtitle),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Documento
        OutlinedTextField(
            value = document,
            onValueChange = {
                document = it
            },

            label = {
                Text(stringResource(R.string.label_document))
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Nombre
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },

            label = {
                Text(stringResource(R.string.label_name))
            },

            modifier = Modifier.fillMaxWidth(),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Correo
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },

            label = {
                Text(stringResource(R.string.label_email))
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Teléfono
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },

            label = {
                Text(stringResource(R.string.label_phone))
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Fecha nacimiento
        OutlinedTextField(
            value = birthDate,
            onValueChange = {
                birthDate = it
            },

            label = {
                Text(stringResource(R.string.label_birthdate))
            },

            placeholder = {
                Text(stringResource(R.string.placeholder_birthdate))
            },

            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },

            label = {
                Text(stringResource(R.string.label_password))
            },

            visualTransformation = PasswordVisualTransformation(),

            modifier = Modifier.fillMaxWidth(),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1353E8),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },

            label = {
                Text(stringResource(R.string.label_confirm_password))
            },

            visualTransformation = PasswordVisualTransformation(),

            modifier = Modifier.fillMaxWidth(),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

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

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1353E8)
            )
        ) {

            Text(
                text = stringResource(R.string.btn_register),
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (message != 0) {

            Text(
                text = stringResource(message),

                color = if (
                    message == R.string.success_register
                ) {
                    Color(0xFF2E7D32)
                } else {
                    Color.Red
                }
            )

            Spacer(modifier = Modifier.height(15.dp))
        }

        Row {

            Text(
                text = stringResource(R.string.text_already_account),
                color = Color.Gray,
                fontSize = 13.sp
            )

            Text(
                text = stringResource(R.string.text_login_here),

                color = Color(0xFF1353E8),

                fontSize = 13.sp,

                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .padding(start = 2.dp)
                    .clickable {

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