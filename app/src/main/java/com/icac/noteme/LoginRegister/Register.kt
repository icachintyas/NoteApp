package com.icac.noteme.LoginRegister

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.icac.noteme.R
import com.icac.noteme.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var button: Button = findViewById(R.id.signup)
        button.setOnClickListener {
            val email = binding.inputemail.text.toString()
            val pass = binding.inputpasssword.text.toString()

            auth = FirebaseAuth.getInstance()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val login = Intent(this, Login::class.java)
                        startActivity(login)
                    }
                }
            } else {
                Toast.makeText(this, "EMPTY FILED NOT ALLOWED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}