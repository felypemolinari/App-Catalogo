package com.example.app_catalogo.data

import android.content.Context
import androidx.room.*

// Criamos as colunas
// A anotação @Entity diz ao Room que isso deve virar uma tabela no SQLite
@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val name: String,
    val password: String
)

// É a interface que contém os comandos SQL
// Usamos @Insert para salvar e @Query para buscar usuários pelo email
@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
}

// Configura o banco de dados Room
// Utilizamos o padrão Singleton ( companion object ) para garantir que só
// exista uma conexão de banco aberta ao mesmo tempo, economizando recursos do celular
@Database(entities = [User::class], version = 2)
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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}