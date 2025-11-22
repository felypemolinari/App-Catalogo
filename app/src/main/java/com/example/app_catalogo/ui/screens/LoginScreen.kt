package com.example.app_catalogo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app_catalogo.data.AppDatabase
import com.example.app_catalogo.data.User
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val db = remember { AppDatabase.getDatabase(context) }
    val userDao = db.userDao()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login",
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // botao entrar
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        scope.launch {
                            val user = userDao.getUserByEmail(email)

                            if (user != null && user.password == password) {

                                // salvar sessao
                                val sharedPref = context.getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
                                with(sharedPref.edit()) {
                                    putString("logged_email", user.email) // Salvamos o email para usar no Perfil
                                    apply()
                                }

                                Toast.makeText(context, "Bem-vindo!", Toast.LENGTH_SHORT).show()
                                navController.navigate("catalogo")
                            } else {
                                Toast.makeText(context, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Entrar", fontSize = 18.sp)
            }

            // botao cadastrar
            OutlinedButton(
                onClick = {
                    navController.navigate("signup")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Não tem conta? Cadastre-se")
            }
        }
    }
}