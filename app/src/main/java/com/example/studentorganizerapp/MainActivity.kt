package com.example.studentorganizerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studentorganizerapp.ui.screens.AddTaskScreen
import com.example.studentorganizerapp.ui.screens.EditTaskScreen
import com.example.studentorganizerapp.ui.screens.TaskListScreen
import com.example.studentorganizerapp.viewmodel.TaskViewModel
import com.example.studentorganizerapp.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            // ✅ ViewModel using factory
            val taskViewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(applicationContext)
            )

            NavHost(navController = navController, startDestination = "taskList") {
                // 📄 Task List
                composable("taskList") {
                    TaskListScreen(navController = navController, taskViewModel = taskViewModel)
                }

                // ➕ Add Task
                composable("addTask") {
                    AddTaskScreen(navController = navController, taskViewModel = taskViewModel)
                }

                // ✏️ Edit Task by ID
                composable(
                    route = "editTask/{taskId}",
                    arguments = listOf(navArgument("taskId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val taskId = backStackEntry.arguments?.getInt("taskId")
                    val task = taskId?.let { taskViewModel.getTaskById(it) }

                    if (task != null) {
                        EditTaskScreen(
                            navController = navController,
                            taskViewModel = taskViewModel,
                            task = task
                        )
                    }
                }
            }
        }
    }
}
