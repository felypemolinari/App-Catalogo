package com.example.app_catalogo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_catalogo.data.AppDatabase
import com.example.app_catalogo.data.User
import com.example.app_catalogo.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Essa biblioteca gerencia a lógica de cadastro e login.

// Estado da UI (UDF - Unidirectional Data Flow)
sealed class AuthState {
    object Idle : AuthState() // Tela parada
    object Loading : AuthState() // Carregando
    object Success : AuthState() // Deu certo
    data class Error(val message: String) : AuthState() // Deu erro
}

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    init {
        val db = AppDatabase.getDatabase(application)
        repository = UserRepository(db.userDao())
    }

    fun register(name: String, email: String, pass: String, confirmPass: String) {
        if (name.isBlank() || email.isBlank() || pass.isBlank()) {
            _authState.value = AuthState.Error("Preencha todos os campos")
            return
        }
        if (pass != confirmPass) {
            _authState.value = AuthState.Error("Senhas não conferem")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading // UI mostra loading

            val existing = repository.login(email)
            if (existing != null) {
                _authState.value = AuthState.Error("Usuário já existe")
            } else {
                val newUser = User(email = email, name = name, password = pass)
                repository.registerUser(newUser) // Chama o repositório
                _authState.value = AuthState.Success // UI navega
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}