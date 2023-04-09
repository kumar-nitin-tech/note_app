package com.example.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context: Context, private val listener: NotesButtonInterface): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNote = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list_items,parent,false))

        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNote[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNote.size

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = allNote[position]
        holder.textView.text = current.text
    }

    fun updater(newList : List<Note>){
        allNote.clear()
        allNote.addAll(newList)

        notifyDataSetChanged()
    }
}

interface NotesButtonInterface {
    fun onItemClicked(note :Note)
}