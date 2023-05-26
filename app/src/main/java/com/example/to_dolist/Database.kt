package com.example.to_dolist

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database (entities = [ToDo_Items::class], version = 1)
abstract class Database: RoomDatabase()   {
    abstract fun toDoDao(): ToDo_DAO

    companion object{
        @Volatile
        private var DATABASE: Database? = null

        fun getDatabaseOb(context: Context): Database{
            if (DATABASE==null)
            {
                synchronized(this){
                    DATABASE = Room.databaseBuilder(context.applicationContext,Database::class.java,"ToDo_Items").build()
                }
            }
            return DATABASE!!
        }
    }
}