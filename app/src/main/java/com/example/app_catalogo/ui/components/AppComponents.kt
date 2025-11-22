package com.example.app_catalogo.ui.components

import androidx.compose.foundation.Image
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
import com.google.android.engage.common.datamodel.Image
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Star

// barra de navegacao inferior
@Composable
fun BottomNavBar(navController: NavController? = null) {
    NavigationBar(containerColor = LightBlue) {

        // home
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {
                navController?.navigate("catalogo")
            }
        )

        // perfil
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = false,
            onClick = {
                navController?.navigate("profile")
            }
        )

        // buscar
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            label = { Text("Buscar") },
            selected = false,
            onClick = { /* Navegar para tela de busca se houver */ }
        )
    }
}

// item da lista horizontal
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

// item da Grid
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
        repeat(3) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
        repeat(2) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun GridItem(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MintGreen)
            .clickable { onClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Image,
                contentDescription = "Imagem do produto",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "TEST",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        RatingStars()
    }
}