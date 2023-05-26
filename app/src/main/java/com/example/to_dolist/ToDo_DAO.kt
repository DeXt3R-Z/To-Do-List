package com.example.to_dolist

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDo_DAO {

    @Upsert
    suspend fun upsertToDo(toDo: ToDo_Items)

    @Delete
    suspend fun deleteToDo(toDo: ToDo_Items)

    @Query ("Select * FROM ToDo_Items ORDER BY uid DESC LIMIT 1")
    fun getToDo(): Flow<ToDo_Items>

    @Query ("Select * FROM ToDo_Items")
    fun getAllToDo(): Flow<List<ToDo_Items>>

}