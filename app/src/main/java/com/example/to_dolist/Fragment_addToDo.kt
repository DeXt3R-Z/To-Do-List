package com.example.to_dolist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Fragment_addToDo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_add_to_do, container, false)
        v.apply {
            var btnAdd = findViewById<Button>(R.id.btnAdd)
            var txt: EditText = findViewById(R.id.etAddToDo)

            var db = Database.getDatabaseOb(context)

            btnAdd.setOnClickListener {

                var item_toDo = txt.text.toString()

                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)

                if (txt.text.toString()!="")
                {
                    GlobalScope.launch() {
                        db.toDoDao().upsertToDo(ToDo_Items(0,item_toDo))
                    }
                }
                else{
                    Toast.makeText(context,"Add a To-Do item to add it to the list", Toast.LENGTH_SHORT).show()
                }

                parentFragmentManager.beginTransaction().remove(this@Fragment_addToDo).commit()
            }
        }
        return v
    }


}