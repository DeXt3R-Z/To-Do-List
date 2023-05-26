package com.example.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn

class MainActivity : AppCompatActivity() {

    lateinit var toDoList: MutableList<ToDo_Items>
    lateinit var adapter: ToDo_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.toDoFragmentHolder,Fragment_ToDoHolder()).commit()


        var btnFab = findViewById<View>(R.id.FabButton)
        btnFab.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                addToBackStack(null)
                add(R.id.FragmentAddToDoHolder,Fragment_addToDo()).commit()
            }
        }
    }
}