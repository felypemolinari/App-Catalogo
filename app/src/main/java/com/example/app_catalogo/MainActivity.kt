package com.example.app_catalogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app_catalogo.ui.navigation.AppNavigation
import com.example.app_catalogo.ui.theme.AppCatalogoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCatalogoTheme {
                // Chama apenas o componente de navegação
                AppNavigation()
            }
        }
    }
}