package com.example.app_catalogo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_catalogo.ui.screens.CatalogoGridScreen
import com.example.app_catalogo.ui.screens.CatalogoScreen
import com.example.app_catalogo.ui.screens.LoginScreen
import com.example.app_catalogo.ui.screens.PostDetailScreen
import com.example.app_catalogo.ui.screens.ProfileScreen
import com.example.app_catalogo.ui.screens.SignupScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignupScreen(navController)
        }

        composable("catalogo") {
            CatalogoScreen(navController)
        }

        // Rota para o Catálogo A
        composable("catalogo_a") {
            CatalogoGridScreen(navController, catalogoTitle = "Catálogo A")
        }

        // Rota para o Catálogo B (Reusa a tela, muda o texto)
        composable("catalogo_b") {
            CatalogoGridScreen(navController, catalogoTitle = "Catálogo B")
        }

        // Rota para o Catálogo C (Reusa a tela, muda o texto)
        composable("catalogo_c") {
            CatalogoGridScreen(navController, catalogoTitle = "Catálogo C")
        }

        composable("post_detail") {
            PostDetailScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }
    }
}