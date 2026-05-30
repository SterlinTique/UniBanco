package com.utp.unibanco.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.utp.unibanco.domain.model.Transaction

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "Hola,",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = state.userName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .padding(16.dp)
        ) {
            // Tarjeta de Saldo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardElevation(defaultElevation = 0.dp).let { 
                    CardDefaults.cardColors(containerColor = Color(0xFF1353E8)) 
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Saldo total",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                    Text(
                        text = state.balance,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Actividad Reciente",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de movimientos
            Card(
                modifier = Modifier.fillMaxWidth().weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(state.transactions) { transaction ->
                        TransactionItem(transaction)
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color(0xFFF4F4F4)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { navController.navigate("auth") {
                    popUpTo("home") { inclusive = true }
                } },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("Cerrar Sesión", color = Color.Black)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = transaction.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Black
            )
            Text(
                text = transaction.date,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Text(
            text = if (transaction.amount > 0) "+ $ ${transaction.amount}" else "- $ ${Math.abs(transaction.amount)}",
            fontWeight = FontWeight.Bold,
            color = if (transaction.amount > 0) Color(0xFF2E7D32) else Color.Black,
            fontSize = 15.sp
        )
    }
}
