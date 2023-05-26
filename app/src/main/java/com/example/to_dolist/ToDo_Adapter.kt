package com.example.to_dolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDo_Adapter(var toDoList:List<ToDo_Items>) : RecyclerView.Adapter<ToDo_Adapter.ToDoViewHolder>() {

    //var isChecked:Int = 0


    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var toDoText = itemView.findViewById<TextView>(R.id.to_do_string)
        var checkbox = itemView.findViewById<View>(R.id.checkBox)
//        init {
//            checkbox.setOnClickListener {
//                if(isChecked == 1){
//                    checkbox.setBackgroundResource(R.drawable.checkbox_checked)
//                    isChecked=0
//
//                }
//                else{
//                    checkbox.setBackgroundResource(R.drawable.checkbox_unchecked)
//                    isChecked=1
//                }
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.to_do_items,parent,false)
        return ToDoViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.apply {
            toDoText.text = toDoList[position].toDoItem
            //isChecked = toDoList[position].isChecked

            var contxt = holder.itemView.context
            var db = Database.getDatabaseOb(contxt)







        }
    }
}