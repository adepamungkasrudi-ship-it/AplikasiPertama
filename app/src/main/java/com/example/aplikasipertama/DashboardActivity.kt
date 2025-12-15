package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Ambil ID dari intent
        val id = intent.getIntExtra("ID", 0)

        val db = AbsensiDatabase.getDatabase(this)
        userDao = db.userDao()

        // Inisialisasi EditText
        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editFirstName = findViewById<EditText>(R.id.editFirstName)
        val editLastName = findViewById<EditText>(R.id.editLastName)

        // Ambil data user dari database
        lifecycleScope.launch(Dispatchers.IO) {
            val user = userDao.getUserById(id)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    editUsername.setText(user.username)
                    editEmail.setText(user.email)
                    editFirstName.setText(user.namaDepan)
                    editLastName.setText(user.namaBelakang)
                }
            }
        }

        // Logout
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)
        buttonLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
