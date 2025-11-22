package com.example.app_catalogo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_catalogo.ui.components.BottomNavBar
import com.example.app_catalogo.ui.components.CatalogoItem

@Composable
fun CatalogoScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text("Catálogo A", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(5) {
                    // CORREÇÃO: Removemos o 'if (index == 0)'
                    // Agora TODOS os itens clicados levam para 'catalogo_a'
                    CatalogoItem(onClick = {
                        navController.navigate("catalogo_a")
                    })
                }
            }

            Text("Catálogo B", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(5) {
                    // CORREÇÃO: Todos levam para 'catalogo_b'
                    CatalogoItem(onClick = {
                        navController.navigate("catalogo_b")
                    })
                }
            }

            Text("Catálogo C", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(5) {
                    // CORREÇÃO: Todos levam para 'catalogo_c'
                    CatalogoItem(onClick = {
                        navController.navigate("catalogo_c")
                    })
                }
            }
        }
    }
}