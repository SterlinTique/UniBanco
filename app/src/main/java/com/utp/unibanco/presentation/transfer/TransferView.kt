package com.utp.unibanco.presentation.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.utp.unibanco.R
import com.utp.unibanco.presentation.components.ShowLoadingAlertDialog
import com.utp.unibanco.presentation.components.ShowMessageAlertDialog

@Composable
fun TransferView(
    senderDocument: String,
    viewModel: TransferViewModel = viewModel(),
    navController: NavController
) {
    var receiver by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

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


            // Título
            Text(
                text = stringResource(R.string.transfer_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Subtítulo
            Text(
                text = stringResource(R.string.transfer_subtitle),
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Documento o teléfono destino
            OutlinedTextField(
                value = receiver,
                onValueChange = { receiver = it },
                label = { Text(stringResource(R.string.label_receiver), color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
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

            // Monto
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text(stringResource(R.string.label_amount), color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
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

            // Botón transferir
            Button(
                onClick = {
                    showLoadingAlert = true
                    viewModel.transfer(senderDocument, receiver, amount) { success, msg ->
                        showLoadingAlert = false
                        if (success) {
                            navController.navigate("home/$senderDocument")
                        } else {
                            titleDialog = R.string.error_transfer
                            messageDialog = R.string.error_empty_fields // o el que corresponda
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
                    text = stringResource(R.string.btn_transfer),
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
