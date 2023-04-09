package com.example.noteapp

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(notes:Note){
        noteDao.insert(notes)
    }

    suspend fun delete(notes: Note){
        noteDao.delete(notes)
    }

}