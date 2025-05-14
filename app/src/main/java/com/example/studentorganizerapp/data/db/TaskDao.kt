package com.example.studentorganizerapp.data.db

import androidx.room.*
import com.example.studentorganizerapp.data.model.Task

@Dao
interface TaskDao {
    // Insert a Task into the database
    @Insert
    suspend fun insertTask(task: Task)

    // Get all Tasks
    @Query("SELECT * FROM tasks ORDER BY date ASC")
    fun getAllTasks(): kotlinx.coroutines.flow.Flow<List<Task>>

    // Get a specific Task by ID
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    // Update a Task
    @Update
    suspend fun updateTask(task: Task)

    // Delete a Task
    @Delete
    suspend fun deleteTask(task: Task)
}
