package com.example.app_catalogo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_catalogo.ui.theme.MintGreen
import com.example.app_catalogo.ui.theme.LightBlue

// Barra de navegação inferior
@Composable
fun BottomNavBar(navController: NavController? = null) { // Adicione esse parâmetro opcional
    NavigationBar(containerColor = LightBlue) {

        // Botão HOME
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false, // Você pode melhorar essa lógica depois verificando a rota atual
            onClick = {
                navController?.navigate("catalogo")
            }
        )

        // Botão PERFIL
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = false,
            onClick = {
                navController?.navigate("profile")
            }
        )

        // Botão BUSCAR (Exemplo)
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            label = { Text("Buscar") },
            selected = false,
            onClick = { /* Navegar para tela de busca se houver */ }
        )
    }
}

// Item da lista horizontal
@Composable
fun CatalogoItem(onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color(0xFFDDEED0))
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Imagem",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(36.dp)
            )
        }
        Text(text = "★★★★★", fontSize = 12.sp)
        Text(text = "TEST", fontSize = 12.sp)
    }
}

// Item da Grid
@Composable
fun GridItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MintGreen)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Placeholder",
            modifier = Modifier.size(60.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "TEST",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Lorem ipsum dolor sit amet...",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(Modifier.height(8.dp))
        RatingStars()
    }
}

@Composable
fun RatingStars() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        // 3 Estrelas cheias
        repeat(3) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(14.dp)
            )
        }
        // 2 Estrelas vazias (ou borda)
        repeat(2) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder, // Usando StarBorder para ficar vazia
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun GridItem(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)) // Cantos arredondados do card
            .background(MintGreen) // Fundo verde claro
            .clickable { onClick() } // Clique para navegar
            .padding(12.dp), // Espaçamento interno
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Caixa da Imagem (Quadrado com borda preta e ícone dentro)
        Box(
            modifier = Modifier
                .size(80.dp) // Tamanho da imagem
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp)), // Borda preta arredondada
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown, // Ícone de imagem
                contentDescription = "Imagem do produto",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Spacer(Modifier.height(8.dp))

        // 2. Título
        Text(
            text = "TEST",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(Modifier.height(4.dp))

        // 3. Texto de Descrição (Pequeno)
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor...",
            fontSize = 10.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            maxLines = 4, // Limita a 4 linhas para não esticar demais
            overflow = TextOverflow.Ellipsis, // Coloca "..." se o texto for muito grande
            lineHeight = 12.sp
        )

        Spacer(Modifier.height(8.dp))

        // 4. Estrelas
        RatingStars()
    }
}