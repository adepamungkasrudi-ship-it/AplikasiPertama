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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PendaftaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContentView(R.layout.activity_pendaftaran)
        // Inisialisasi komponen dari layout
        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editFirstName = findViewById<EditText>(R.id.editFirstName)
        val editLastName = findViewById<EditText>(R.id.editLastName)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editConfirmPassword = findViewById<EditText>(R.id.editPassword)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // Tombol Submit
        btnSubmit.setOnClickListener {
            val username = editUsername.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val firstName = editFirstName.text.toString().trim()
            val lastName = editLastName.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val confirmPassword = editConfirmPassword.text.toString().trim()

            // Validasi input
            when {
                username.isBlank() || email.isBlank() ||
                        firstName.isBlank() || lastName.isBlank() ||
                        password.isBlank() || confirmPassword.isBlank() -> {
                    Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
                }

                password != confirmPassword -> {
                    Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                }


                else -> {
                    val userEntity = UserEntity(
                        username= username,
                        email= email,
                        namaDepan = firstName,
                        namaBelakang = lastName,
                        password = password
                    )
                    val db = AbsensiDatabase.getDatabase(this)
                    lifecycleScope.launch (Dispatchers.IO){
                        db.userDao().insertUser(userEntity)
                    }
                    val fullName = "$firstName $lastName"
                    Toast.makeText(this, "Pendaftaran berhasil: $fullName", Toast.LENGTH_LONG).show()


                    //pindah ke halaman dashboard
                    val intentPindahDashboard = Intent (this, DashboardActivity::class.java)
                    intentPindahDashboard.putExtra("USERNAME", editUsername.text.toString())
                    intentPindahDashboard.putExtra("EMAIL",editEmail.text.toString() )
                    intentPindahDashboard.putExtra("NAMA_DEPAN", editFirstName.text.toString())
                    intentPindahDashboard.putExtra("NAMA_BELAKANG",editLastName.text.toString())
                    startActivity(intentPindahDashboard)

                }
            }
        }


        val buttonCancel = findViewById<Button>(R.id.btnCancel)
        buttonCancel.setOnClickListener {
            val intentPindahLogin = Intent(this, MainActivity::class.java)
            startActivity(intentPindahLogin)
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}