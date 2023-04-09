package com.example.noteapp


import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    //to Insert a note we create a abstract function to insert the notes
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query(value = "Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}