package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotesButtonInterface {
    private lateinit var viewModel: NoteViewModel
    private lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updater(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} is deleted", Toast.LENGTH_SHORT).show()
    }
    fun submit(view: View) {
        val input = findViewById<EditText>(R.id.textField)
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText is inserted", Toast.LENGTH_SHORT).show()
            input.text.clear()
        }
    }
}