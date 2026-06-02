package com.utp.unibanco.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.utp.unibanco.presentation.components.ShowLoadingAlertDialog

import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.ui.res.stringResource
import com.utp.unibanco.R
import com.utp.unibanco.domain.model.Movement
import androidx.compose.ui.platform.LocalLocale

@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(),
    document: String? = null
) {
    val user by viewModel.userState
    val isLoading by viewModel.isLoading
    val account by viewModel.accountState
    val movements by viewModel.movementsState

    LaunchedEffect(document) {
        document?.let {
            viewModel.loadHomeData(it)
        }
    }

    if (isLoading) {
        ShowLoadingAlertDialog()
    }

    Scaffold(
        topBar = {
            HomeTopBar(userName = user?.name ?: "Usuario")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF4F4F4))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BalanceCard(balance = account?.balance ?: 0.0)

            Spacer(modifier = Modifier.height(24.dp))

            QuickActionsSection(navController = navController)

            Spacer(modifier = Modifier.height(24.dp))

            RecentTransactionsSection(movements)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(userName: String) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = stringResource(R.string.home_greeting),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = userName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones", tint = Color(0xFF1353E8))
            }
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color(0xFF1353E8))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun BalanceCard(balance: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1353E8))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.home_balance_label),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$ ${String.format(LocalLocale.current.platformLocale, "%,.0f", balance)}",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun QuickActionsSection(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionButton(icon = Icons.AutoMirrored.Filled.Send, label = stringResource(R.string.home_action_transfer))
        QuickActionButton(icon = Icons.Default.CreditCard, label = stringResource(R.string.home_action_cards), onClick= {navController.navigate("card")})
        QuickActionButton(icon = Icons.Default.AccountBalanceWallet, label = stringResource(R.string.home_action_payments))
        QuickActionButton(icon = Icons.Default.History, label = stringResource(R.string.home_action_history))
    }
}

@Composable
fun QuickActionButton(icon: ImageVector, label: String, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clickable {onClick ()}
                .background(Color.White, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = Color(0xFF1353E8))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun RecentTransactionsSection(movements: List<Movement>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.home_recent_transactions),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                if (movements.isEmpty()) {
                    Text(
                        text = stringResource(R.string.home_not_transactions),
                        color = Color.Gray
                    )
                } else {
                    movements.forEachIndexed { index, movement ->
                        TransactionItem(
                            title = movement.type.replaceFirstChar {
                                it.uppercase()
                            },
                            amount = movement.amount,
                            date = movement.date,
                            isDeposit = movement.type == "deposito"
                        )
                        if (index < movements.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItem(title: String, amount: Double, date: String, isDeposit: Boolean){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = date, fontSize = 12.sp, color = Color.Gray)
        }
        Text(
            text =
                if (isDeposit)
                    "+ $ ${String.format("%,.0f", amount)}"
                else
                    "- $ ${String.format("%,.0f", amount)}",

            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,

            color =
                if (isDeposit)
                    Color(0xFF4CAF50)
                else
                    Color.Red
        )
    }
}
