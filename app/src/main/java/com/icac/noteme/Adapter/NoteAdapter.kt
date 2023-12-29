package com.icac.noteme.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icac.noteme.R
import com.icac.noteme.Room.Note

class NoteAdapter (private val notes: ArrayList<Note>, private val listener: OnAdapterListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.noteTitle)
        val body = view.findViewById<TextView>(R.id.noteBody)
        val date = view.findViewById<TextView>(R.id.noteDate)
        val btn = view.findViewById<TextView>(R.id.btnClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_note, parent, false)
        return NoteViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.body.text = note.note
        holder.date.text = note.date
        holder.btn.setOnClickListener {
            listener.onClick(note)
        }
        holder.btn.setOnClickListener {
            listener.onClick(note)
        }

    }

    override fun getItemCount() = notes.size

    fun setData(list: List<Note>) {
        notes.clear()
        Log.d("Debug Set Data", "RESPONSE: $list")
        notes.addAll(list)
    }

    interface OnAdapterListener {
        fun onClick(note: Note)
        fun onUpdate(note: Note)
    }

}