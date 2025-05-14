package com.example.studentorganizerapp.data.repository

import com.example.studentorganizerapp.data.db.TaskDao
import com.example.studentorganizerapp.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    // Insert a Task
    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    // Get all Tasks
    fun getAllTasks(): kotlinx.coroutines.flow.Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    // Get a Task by ID
    suspend fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }

    // Update a Task
    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    // Delete a Task
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}
