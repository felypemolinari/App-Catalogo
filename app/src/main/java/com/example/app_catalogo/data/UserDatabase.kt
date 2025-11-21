package com.example.app_catalogo.data

import android.content.Context
import androidx.room.*

// 1. ALTERAÇÃO AQUI: Adicionamos 'name'
@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val name: String, // <--- Novo campo
    val password: String
)

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
}

@Database(entities = [User::class], version = 2) // Mudei a versão para 2 (mas desinstale o app!)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_catalogo_db"
                )
                    .fallbackToDestructiveMigration() // <--- Adicione isso para ele recriar o banco se mudar a versão
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}