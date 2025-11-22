# App Catálogo

> Aplicativo nativo Android desenvolvido para gestão e visualização de catálogo de produtos. O projeto demonstra a implementação de uma arquitetura moderna, escalável e reativa, integrando persistência local (SQLite) e nuvem (Firebase).

---

## 🚀 Funcionalidades

* **Autenticação Completa:**
    * Login e Cadastro de usuários.
    * Validação de campos e confirmação de senha.
    * Persistência de sessão (Login automático).
* **Navegação:**
    * Fluxo fluido entre telas usando **Navigation Compose**.
    * Telas: Login -> Cadastro -> Home (Catálogo) -> Detalhes -> Perfil.
* **Catálogo de Produtos:**
    * Listagem em grade (Grid) adaptável.
    * Visualização detalhada do produto.
* **Gerenciamento de Dados (Híbrido):**
    * **Offline First:** Uso do **Room Database** para garantir acesso instantâneo e funcionamento sem internet.
    * **Cloud Backup:** Sincronização dos dados de cadastro com **Firebase Firestore**.
* **Perfil do Usuário:**
    * Visualização de dados do usuário logado recuperados do banco local.
    * Funcionalidade de Logout.

---

## 🏛️ Arquitetura e Padrões de Projeto

Este projeto foi desenvolvido seguindo estritamente o **Guia de Arquitetura de Apps do Google**, priorizando a separação de responsabilidades e a testabilidade.

### Padrão MVVM (Model-View-ViewModel)

A aplicação está dividida em camadas claras:

1.  **UI Layer (View):**
    * Implementada com **Jetpack Compose (Material Design 3)**.
    * As telas (`Screens`) são passivas: elas apenas observam o estado e enviam eventos de clique.
    * Não há lógica de negócios ou chamadas de banco de dados nas telas.

2.  **ViewModel (State Holder):**
    * Gerencia o estado da UI (ex: `Loading`, `Success`, `Error`).
    * Implementa o **Fluxo Unidirecional de Dados (UDF)** usando `StateFlow`.
    * Sobrevive a mudanças de configuração (rotação de tela).

3.  **Data Layer (Repository Pattern):**
    * **Repository:** Atua como a "Fonte Única de Verdade" (Single Source of Truth). Ele decide se busca os dados localmente ou na nuvem, abstraindo essa complexidade da UI.
    * **Data Sources:**
        * **Local:** Room Database (SQLite) via DAO.
        * **Remoto:** Firebase Firestore SDK.

---

## 📂 Estrutura do Projeto

A organização de pastas reflete a arquitetura adotada:

```text
com.example.app_catalogo
 ┣ data              # Camada de Dados
 ┃ ┣ User.kt         # Modelo (Entity)
 ┃ ┣ UserDao.kt      # Acesso ao SQLite
 ┃ ┣ UserDatabase.kt # Configuração do Room
 ┃ ┗ UserRepository.kt # Regra de dados (Local + Cloud)
 ┃
 ┣ ui                # Camada de Interface
 ┃ ┣ components      # Componentes reutilizáveis (Botões, Cards)
 ┃ ┣ navigation      # Configuração de Rotas (NavHost)
 ┃ ┣ screens         # Telas (Login, Catalog, Profile...)
 ┃ ┣ theme           # Tema e Cores
 ┃ ┗ viewmodel       # Gestão de Estado
 ┃   ┗ AuthViewModel.kt
 ┃
 ┗ MainActivity.kt   # Ponto de entrada

## Autores
Felype Cesar Molinari
Carlos Henrique Okarenski Ramos Depieri