package com.example.studentorganizerapp.viewmodel

import androidx.lifecycle.*
import com.example.studentorganizerapp.data.model.Task
import com.example.studentorganizerapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    // All tasks observed by UI
    val allTasks: LiveData<List<Task>> = repository.getAllTasks().asLiveData()

    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    // ✅ Get a single task by ID from allTasks LiveData
    fun getTaskById(taskId: Int): Task? {
        return allTasks.value?.find { it.id == taskId }
    }
}
