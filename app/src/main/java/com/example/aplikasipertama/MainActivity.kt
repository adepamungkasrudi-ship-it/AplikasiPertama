package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.editTextusername)
        val password = findViewById<EditText>(R.id.editTextpassword)


        var buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener{

            var usernameText = username.text.toString()
            var passwordText = password.text.toString()

            if (usernameText.isBlank()|| passwordText.isBlank()){

            Toast.makeText(this,"Username & Password tidak boleh kosong",
                Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this,"Loging sedang diproses",
                Toast.LENGTH_LONG).show()

        }

        }
        val buttonDaftar = findViewById<Button>(R.id.buttonDaftar)
        buttonDaftar.setOnClickListener {
            val intenPindahPendaftaran = Intent(this,PendaftaranActivity::class.java)
            startActivity(intenPindahPendaftaran)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}