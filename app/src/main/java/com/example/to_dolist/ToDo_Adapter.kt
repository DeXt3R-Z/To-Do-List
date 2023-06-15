package com.example.to_dolist

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class ToDo_Adapter(var toDoList:List<ToDo_Items>) : RecyclerView.Adapter<ToDo_Adapter.ToDoViewHolder>() {


    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var toDoText = itemView.findViewById<TextView>(R.id.to_do_string)
        var checkbox = itemView.findViewById<CheckBox>(R.id.checkBox)

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
            //toDoText.text = toDoList[position].toDoItem
            checkbox.buttonTintList =  ColorStateList.valueOf(Color.parseColor("#FF403632"))
            var contxt = holder.itemView.context
            var db = Database.getDatabaseOb(contxt)
            CoroutineScope(Dispatchers.IO).launch {
                var checkedState = db.toDoDao().getCheckedState(position)
                if (checkedState == 1)
                {
                    withContext(Dispatchers.Main)
                    {
                        checkbox.isChecked = true
                        toDoText.apply {
                            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                            text = toDoList[position].toDoItem
                        }
                    }
                }
                else if(checkedState == 0)
                {
                    withContext(Dispatchers.Main)
                    {
                        //checkbox.isChecked = false
                        toDoText.text = toDoList[position].toDoItem
                    }
                }
            }


            checkbox.setOnClickListener {
                var fragment_ToDoHolder = Fragment_ToDoHolder()
                fragment_ToDoHolder.resetRecyclerView()
                if(checkbox.isChecked)
                {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.toDoDao().updateCheckBoxState(position, 1)
                    }
                    toDoText.apply {
                        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        text = toDoList[position].toDoItem
                    }
                }
                else
                {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.toDoDao().updateCheckBoxState(position, 0)
                    }
                    toDoText.apply {
                        paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                        text = toDoList[position].toDoItem
                    }
                }

            }




        }
    }
}