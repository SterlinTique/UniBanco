package com.utp.unibanco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utp.unibanco.auth.AuthView

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

        composable("home") {
            //HomeView(navController = navController)
        }
        /*
        composable("register") {
            RegisterView(navController = navController)
        }
        */
    }
}