package com.icac.noteme

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.icac.noteme.Room.Note
import com.icac.noteme.Room.NoteApp
import com.icac.noteme.databinding.ActivityNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private var noteId: Int = 0

    var idNote = 0
    var type = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addNoteListener()
        setView()

        val bundle = intent.extras
        if (bundle != null){
            idNote = bundle.getInt("ID")
            type = bundle.getInt("TYPE")
            Toast.makeText(this, idNote.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun setView(){
        val addBtn = findViewById<Button>(R.id.btnAdd)
        val title = findViewById<TextView>(R.id.title)
        val note = findViewById<TextView>(R.id.note)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDel = findViewById<Button>(R.id.btnDelete)
        val hiden = findViewById<LinearLayout>(R.id.hidenBtn)
        val intentType = intent.getIntExtra("TYPE", 0)
        when (intentType) {
            Constant.CREATE -> {

            }
            Constant.READ -> {
                hiden.visibility = View.VISIBLE
                btnAdd.visibility = View.GONE
                getNote()
            }
        }
    }

    fun getNote(){
        val title = findViewById<TextView>(R.id.title)
        val note = findViewById<TextView>(R.id.note)
        noteId = intent.getIntExtra("ID", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val result = NoteApp(this@NoteActivity).getNoteDao().getNote(noteId)[0]
            title.setText(result.title)
            note.setText(result.note)
        }

    }

    fun addNoteListener() {
        val addBtn = findViewById<Button>(R.id.btnAdd)
        addBtn.setOnClickListener {
            val title = findViewById<TextView>(R.id.title)
            val note = findViewById<TextView>(R.id.note)
            val date = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = date.format(Date())
            Log.d("TEST TANGGAL", "RESPON: ${currentDate.toString()}")
            CoroutineScope(Dispatchers.IO).launch {
                NoteApp(this@NoteActivity).getNoteDao().addNote(
                    Note(0, title.text.toString(), note.text.toString(), currentDate.toString())
                )
            }
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
            finish()
        }
    }
}