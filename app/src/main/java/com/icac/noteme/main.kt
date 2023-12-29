package com.icac.noteme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.icac.noteme.Adapter.NoteAdapter
import com.icac.noteme.Room.Note
import com.icac.noteme.Room.NoteApp
import com.icac.noteme.Room.NoteDao
import com.icac.noteme.databinding.ActivityMainBinding
import com.icac.noteme.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [main.newInstance] factory method to
 * create an instance of this fragment.
 */
class main : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteList: RecyclerView
    private lateinit var dao: NoteDao

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        newListener()
        setRecycler()
    }

    private fun newListener() {
        val btnNew = view?.findViewById<Button>(R.id.newBtn)
        btnNew?.setOnClickListener {
//            val intent = Intent(this, NoteActivity::class.java)
//            startActivity(intent)
            intentEdit(0, Constant.CREATE)
        }
    }

    fun intentEdit(noteId: Int, intentType: Int) {
        val bundle = Bundle()
//        activity?.startActivity(
//            Intent(activity, NoteActivity::class.java)
//                .putExtra("ID", noteId)
//                .putExtra("TYPE", intentType)
//        )
        bundle.putInt("ID", noteId)
        bundle.putInt("TYPE", intentType)
        val goAct = Intent(activity, NoteActivity::class.java)
        goAct.putExtras(bundle)
        activity?.startActivity(goAct)

    }

    private fun setRecycler() {
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener{
            override fun onClick(note: Note) {
                intentEdit(note.id, Constant.READ)
            }

        })
        val notelistt = view?.findViewById<RecyclerView>(R.id.recycler)
//        noteList = findViewById(R.id.recycler)
        notelistt?.apply {
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
            adapter = noteAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val notes = NoteApp.invoke(requireContext()).getNoteDao().getAllNote()
            Log.d("MAIN", "ResponseDB: $notes")
            withContext(Dispatchers.IO) {
                noteAdapter.setData(notes)
            }
        }
        setRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_main, container, false)

        val btnNew = view.findViewById<Button>(R.id.newBtn)
        btnNew.setOnClickListener {
//            val intent = Intent(this, NoteActivity::class.java)
//            startActivity(intent)
            intentEdit(0, Constant.CREATE)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment main.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            main().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}