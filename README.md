# 📝 To-Do List Application

Bienvenue dans l'application **To-Do List** !  
Cette application, développée en **Kotlin**, vous permet de créer, gérer et organiser vos tâches efficacement grâce à une interface simple et intuitive.

---

## 📋 Fonctionnalités

### 1. Ajouter une tâche
- Permet de créer une nouvelle tâche avec les informations suivantes :
  - **Titre** : Obligatoire.
  - **Description** : Optionnelle.
  - **Statut** : Par défaut, la tâche est définie comme "En cours".
  - **Date de création** : Générée automatiquement.

---

### 2. Visualiser les tâches
#### Tâches en cours
- Affiche une liste de toutes les tâches marquées comme "En cours".

#### Tâches terminées
- Affiche une liste de toutes les tâches marquées comme "Terminées".

---

### 3. Voir les détails d'une tâche
- Affiche les informations complètes d'une tâche sélectionnée :
  - Titre
  - Description
  - Statut (En cours / Terminée)
  - Date de création

---

### 4. Modifier une tâche
- Permet de modifier les informations d'une tâche existante :
  - **Titre**
  - **Description**
  - **Statut** : Une tache peux etre marqué comme terminé et elle et le statut change automatiquement.

---

### 5. Supprimer une tâche
- Supprime définitivement une tâche après confirmation.

---

## 🛠️ Technologies Utilisées

- **Langage principal** : Kotlin
- **Plateforme** : Développement mobile natif pour Android
- **Base de données locale** : Room (ORM pour SQLite) **NB** : La base de donnéè n'a pas encore été implimenté , elle apparaitra donc comme fonctionnalité a venir.
- **Design** : Jetpack Compose ou XML (selon votre choix)
- **Architecture** : MVVM (Model-View-ViewModel)

---

## ⚙️ Installation

### 1. Pré-requis
- Android Studio installé sur votre machine.
- JDK version 8 ou supérieure.
- Un émulateur ou un appareil Android connecté.

   ```bash
   git checkout -b nouvelle-fonctionnalite
   ```

### 2. Clonez ce dépôt

   ```bash
git clone https://github.com/votre-utilisateur/todo-list-app.git
cd todo-list-app
   ```

### 3. Ouvrez le projet dans Android Studio
- Lancez Android Studio.
- Cliquez sur **File > Open** et sélectionnez le dossier du projet.

### 4. Lancez l'application
- Configurez un émulateur Android ou connectez un appareil physique.
- Cliquez sur le bouton **Run** dans Android Studio.

---

## 📄 Modèle de Données

### Exemple de schéma pour la base de données Room :

```kotlin
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "status") val status: String = "En cours", // "En cours" ou "Terminée"
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)
```

---

## 🚀 Fonctionnalités à venir

- Système de notification pour les tâches en retard.
- Ajout de catégories pour mieux organiser les tâches.
- Fonctionnalité de recherche et de filtrage.
- Mode sombre pour l'interface utilisateur.

---

## 🐛 Contribution

Les contributions sont les bienvenues ! Si vous trouvez un bug ou souhaitez ajouter une nouvelle fonctionnalité :

1. Forkez ce dépôt.
2. Créez une branche pour vos modifications :
   ```bash
   git checkout -b nouvelle-fonctionnalite
   ```
3. Faites vos modifications et ajoutez un commit :
   ```bash
   git commit -m "Ajout d'une nouvelle fonctionnalité"
   ```
4. Poussez vos modifications :
   ```bash
   git push origin nouvelle-fonctionnalite
   ```
5. Ouvrez une Pull Request.

---

## 📧 Contact

Pour toute question ou suggestion, vous pouvez me contacter à : [votre.email@example.com](mailto:djouakalionel4@gmail.com.com).

---

**✨ Merci d'utiliser cette application To-Do List développée en Kotlin !**
```
