package com.icac.noteme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.icac.noteme.Adapter.NoteAdapter
import com.icac.noteme.Room.Note
import com.icac.noteme.Room.NoteApp
import com.icac.noteme.Room.NoteDao
import com.icac.noteme.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteList: RecyclerView
    private lateinit var dao: NoteDao

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replace(main())
    }


    private fun replace(fragment: Fragment) {
        val managerFrag = supportFragmentManager
        val transFrag = managerFrag.beginTransaction()
        transFrag.replace(
            R.id.frame, fragment
        )
        transFrag.commit()
    }

}