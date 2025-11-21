package com.example.app_catalogo.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // Importante para os estados
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_catalogo.data.AppDatabase
import com.example.app_catalogo.ui.components.BottomNavBar
import com.example.app_catalogo.ui.theme.LightBlue
import com.example.app_catalogo.ui.theme.MintGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val userDao = db.userDao()

    // Estados para guardar os dados que vêm do banco
    var userName by remember { mutableStateOf("Carregando...") }
    var userEmail by remember { mutableStateOf("...") }

    // --- EFEITO PARA BUSCAR DADOS AO ABRIR A TELA ---
    LaunchedEffect(Unit) {
        scope.launch {
            // 1. Pega o email salvo no Login
            val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val emailSalvo = sharedPref.getString("logged_email", null)

            if (emailSalvo != null) {
                // 2. Busca no banco
                val user = userDao.getUserByEmail(emailSalvo)
                if (user != null) {
                    userName = user.name // Atualiza a tela com o Nome
                    userEmail = user.email // Atualiza a tela com o Email
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Perfil") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = LightBlue)
            )
        },
        bottomBar = { BottomNavBar(navController) },
        containerColor = LightBlue
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- CABEÇALHO ---
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Color.Gray
                    )
                }
                // Ícone de edição
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MintGreen)
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- MOSTRANDO OS DADOS REAIS ---
            Text(
                text = userName, // <--- Nome do Banco
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = userEmail, // <--- Email do Banco
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- MENUS (Igual ao anterior) ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                ProfileOptionItem(icon = Icons.Outlined.Person, title = "Meus Dados") {}
                Divider(color = LightBlue.copy(alpha = 0.5f))
                ProfileOptionItem(icon = Icons.Outlined.Notifications, title = "Notificações") {}
                Divider(color = LightBlue.copy(alpha = 0.5f))
                ProfileOptionItem(icon = Icons.Outlined.Lock, title = "Segurança e Senha") {}
                Divider(color = LightBlue.copy(alpha = 0.5f))
                ProfileOptionItem(icon = Icons.Outlined.ThumbUp, title = "Ajuda e Suporte") {}
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- BOTÃO SAIR ---
            Button(
                onClick = {
                    // Limpar a sessão ao sair
                    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    navController.navigate("login") { popUpTo(0) }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2)),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.Red)
                Spacer(Modifier.width(8.dp))
                Text("Sair da Conta", color = Color.Red)
            }
        }
    }
}

@Composable
fun ProfileOptionItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color.DarkGray)
        Spacer(Modifier.width(16.dp))
        Text(title, modifier = Modifier.weight(1f), fontSize = 16.sp)
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
    }
}