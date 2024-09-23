package com.example.myapplication3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.loginButton)
        val errorMessage: TextView = findViewById(R.id.errorMessage)

        // login
        val correctUsername = "jose"
        val correctPassword = "admin"

        // Ação do botão de login
        loginButton.setOnClickListener {
            val enteredUsername = usernameEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            // Verificar se o login está correto
            if (enteredUsername == correctUsername && enteredPassword == correctPassword) {
                // Se correto, enviar para a segunda atividade
                val intent = Intent(this, WelcomeActivity::class.java).apply {
                    putExtra("USERNAME", enteredUsername)
                }
                startActivity(intent)
                finish()
            } else {
                errorMessage.visibility = TextView.VISIBLE
                usernameEditText.text.clear()
                passwordEditText.text.clear()
            }
        }
    }
}