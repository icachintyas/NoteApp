package com.icac.noteme.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.icac.noteme.MainActivity
import com.icac.noteme.R
import com.icac.noteme.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val button: Button = findViewById(R.id.button)
        val daftar: TextView = findViewById(R.id.button_daftar)

        daftar.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val email = binding.textinputUsername.text.toString()
            val pass = binding.textinputPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "WRONG PASSWORD / EMAIL", Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "EMPTY FILED NOT ALLOWED", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if (auth.currentUser != null)
//            startActivity(Intent(this, MainActivity::class.java))
//    }

}