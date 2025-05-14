package com.example.studentorganizerapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentorganizerapp.data.db.AppDatabase
import com.example.studentorganizerapp.data.repository.TaskRepository

class TaskViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ensure that the modelClass is the correct one (TaskViewModel)
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            val taskDao = AppDatabase.getDatabase(context).taskDao()
            val repository = TaskRepository(taskDao)
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
