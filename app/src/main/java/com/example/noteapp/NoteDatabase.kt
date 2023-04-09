package com.example.noteapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

        abstract fun getNoteDao(): NoteDao

        //Creating a Singleton prevents multiple instances of database opening at the same time.
        companion object{

            @Volatile
            private var INSTANCE:NoteDatabase ?= null

            fun getDatabase(context: Context): NoteDatabase {
                return INSTANCE ?: synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "notes_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
}
