package com.example.app_catalogo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_catalogo.ui.components.BottomNavBar
import com.example.app_catalogo.ui.components.GridItem
import com.example.app_catalogo.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoGridScreen(
    navController: NavController,
    catalogoTitle: String
) {
    Scaffold(
        // 1. A Barra Superior (TopBar) deve estar aqui dentro
        topBar = {
            TopAppBar(
                title = { Text(catalogoTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Ação de config */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configurações")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlue // Garante que a cor combine com o fundo
                )
            )
        },
        // 2. A Barra Inferior (BottomBar) deve estar aqui dentro
        bottomBar = { BottomNavBar(navController) },
        containerColor = LightBlue
    ) { innerPadding -> // 'innerPadding' é o espaço seguro entre as barras

        // 3. O Conteúdo (Grid)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),

            // AQUI ESTÁ O SEGREDO: Somamos o padding das barras + 16dp de margem visual
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(0.dp) // O padding é controlado pelo contentPadding acima
        ) {
            items(6) {
                GridItem(onClick = {
                    navController.navigate("post_detail")
                })
            }
        }
    }
}