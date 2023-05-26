package com.example.to_dolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class Fragment_ToDoHolder : Fragment() {


    lateinit var todoAdapter: ToDo_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_to_do_holder, container, false)
        v.apply {
            var recyclerView: RecyclerView = findViewById(R.id.toDoFragmentHolder)
            var db = Database.getDatabaseOb(context)


            GlobalScope.launch(Dispatchers.Main) {
                db.toDoDao().getAllToDo().flowOn(Dispatchers.IO).collect{
                    if (it.isNotEmpty())
                    {
                        todoAdapter = ToDo_Adapter(it)
                        recyclerView.adapter = todoAdapter
                        recyclerView.layoutManager = LinearLayoutManager(context.applicationContext)
                    }
                }
            }
        }

        return v
    }

}