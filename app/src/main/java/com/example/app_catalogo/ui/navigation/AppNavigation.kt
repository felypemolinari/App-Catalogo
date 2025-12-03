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
    // Define o NavHost, que é o container onde as telas são trocadas
    val navController = rememberNavController()

    // O NavHost é o container gráfico que troca as telas com base na rota atual
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Mapeamento das rotas (Strings/URLs) para as Telas (Composables)

        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignupScreen(navController)
        }

        composable("catalogo") {
            CatalogoScreen(navController)
        }

        composable("catalogo_a") {
            CatalogoGridScreen(navController, catalogoTitle = "Catálogo A")
        }

        composable("catalogo_b") {
            CatalogoGridScreen(navController, catalogoTitle = "Catálogo B")
        }

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