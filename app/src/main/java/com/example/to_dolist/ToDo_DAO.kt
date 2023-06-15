package com.example.to_dolist

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDo_DAO {

    @Upsert
    suspend fun upsertToDo(toDo: ToDo_Items)

    @Query("UPDATE ToDo_Items SET isChecked = :state WHERE uid = :position")
    suspend fun updateCheckBoxState(position: Int, state: Int)

    @Delete
    suspend fun deleteToDo(toDo: ToDo_Items)

    @Query ("Select * FROM ToDo_Items ORDER BY uid DESC LIMIT 1")
    fun getToDo(): Flow<ToDo_Items>

    @Query ("Select * FROM ToDo_Items")
    fun getAllToDo(): Flow<List<ToDo_Items>>

    @Query ("Select isChecked FROM ToDo_Items WHERE uid = :position ")
    fun getCheckedState(position: Int): Int

}