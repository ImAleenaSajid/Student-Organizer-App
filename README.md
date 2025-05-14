# 📚 Student Organizer App

A student productivity app built with **Kotlin**, **Jetpack Compose**, and **Room Database**, enabling users to manage their academic tasks effectively. With a clean UI and intuitive interaction, users can add, edit, and delete tasks seamlessly.

---

## ✨ Features

* 📋 View all tasks
* ➕ Add a new task (with title, date, and category)
* ✏️ Edit existing tasks
* 🗑️ Delete tasks
* 💾 Persistent storage using Room Database
* ⚙️ MVVM architecture with LiveData and ViewModel
* 🎨 Material3 design using Jetpack Compose

---

## 🛠 Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose
* **Database:** Room (SQLite)
* **Architecture:** MVVM
* **Navigation:** Jetpack Navigation
* **State Management:** LiveData + ViewModel
* **Design System:** Material3

---

## 📱 Screens

| Task List Screen                         | Add Task Screen | Edit Task Screen     |
| ---------------------------------------- | --------------- | -------------------- |
| Shows all tasks with delete/edit options | Add a new task  | Modify existing task |

---

## 📂 Project Structure

```
├── data
│   ├── database
│   │   └── TaskDatabase.kt
│   ├── model
│   │   └── Task.kt
│   ├── repository
│   │   └── TaskRepository.kt
│   └── dao
│       └── TaskDao.kt
├── ui
│   ├── screens
│   │   ├── TaskListScreen.kt
│   │   ├── AddTaskScreen.kt
│   │   └── EditTaskScreen.kt
│   └── components
│       └── TaskItem.kt
├── viewmodel
│   └── TaskViewModel.kt
└── MainActivity.kt
```

---

## 💡 How to Use

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. **Open the project in Android Studio.**

3. **Build and run the app on an emulator or physical device.**

4. **Navigate between screens:**

   * On the **Task List Screen**, view, delete, and edit tasks.
   * On the **Add Task Screen**, input task details and save.
   * On the **Edit Task Screen**, modify task details and save.

---

## 🚀 Installation

1. Clone the repository:

   ```bash
   git clone <repository-url>
   ```

2. Open the project in **Android Studio**.

3. Install dependencies and sync the project with Gradle.

4. Run the app on a connected device or emulator.

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Please Watch the Video Attached
### Made By Aleena Sajid
