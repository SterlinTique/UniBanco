package com.utp.unibanco.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.utp.unibanco.presentation.card.CardView
import com.utp.unibanco.presentation.home.HomeView
import com.utp.unibanco.presentation.login.AuthView
import com.utp.unibanco.presentation.register.RegisterView
import com.utp.unibanco.presentation.transfer.TransferView

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {

        composable("auth") {
            AuthView(navController = navController)
        }

        composable(
            "home/{document}",
            arguments = listOf(navArgument("document") { type = NavType.StringType })
        ) { backStackEntry ->
            val document = backStackEntry.arguments?.getString("document")
            HomeView(navController = navController, document = document)
        }

        composable("register") {
            RegisterView(navController = navController)
        }

        composable("card") {
            CardView(navController = navController)
        }

        composable("transfer/{senderDocument}",
            arguments = listOf(navArgument("senderDocument") { type = NavType.StringType })
        ) { backStackEntry ->
            val senderDocument = backStackEntry.arguments?.getString("senderDocument") ?: ""
            TransferView(senderDocument = senderDocument, navController = navController)
        }


    }
}