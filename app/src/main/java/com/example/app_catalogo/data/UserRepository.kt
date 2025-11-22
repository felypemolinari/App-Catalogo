package com.example.app_catalogo.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// essa classe gerencia de onde vem os dados (SQLite + Firebase)
class UserRepository(private val userDao: UserDao) {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun registerUser(user: User) {
        // salva Local (SQLite)
        userDao.insert(user)

        // salva na Nuvem (Firebase - Requisito)
        val firebaseUser = hashMapOf(
            "name" to user.name,
            "email" to user.email
        )
        try {
            firestore.collection("users").document(user.email).set(firebaseUser).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun login(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}