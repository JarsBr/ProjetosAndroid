package com.example.cronometro

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cronometro.R

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var running = false
    private lateinit var timeView: TextView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        timeView = findViewById(R.id.textView)

        // Recuperar estado após rotação de tela, se necessário
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
        }

        // Iniciar o cronômetro
        val startButton = findViewById<Button>(R.id.start)
        startButton.setOnClickListener { onClickStart(it) }

        // Parar o cronômetro
        val stopButton = findViewById<Button>(R.id.Stop)
        stopButton.setOnClickListener { onClickStop(it) }

        // Resetar o cronômetro
        val resetButton = findViewById<Button>(R.id.reset)
        resetButton.setOnClickListener { onClickReset(it) }

        runTimer() // Executa o cronômetro
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
    }

    // Iniciar o cronômetro
    fun onClickStart(view: View) {
        running = true
    }

    // Parar o cronômetro
    fun onClickStop(view: View) {
        running = false
    }

    // Resetar o cronômetro
    fun onClickReset(view: View) {
        running = false
        seconds = 0
    }

    // Executa o cronômetro e atualiza o TextView a cada segundo
    private fun runTimer() {
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format("%02d:%02d:%02d", hours, minutes, secs)
                timeView.text = time

                if (running) {
                    seconds++
                }

                handler.postDelayed(this, 1000) // Executa novamente após 1 segundo
            }
        })
    }
}
