package com.example.to_dolist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo_Items (
    @PrimaryKey (autoGenerate = true)
    var uid: Int,
    var toDoItem: String
)