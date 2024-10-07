package com.example.cronometro_temporizador

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class temporizador : AppCompatActivity() {
    private var timeInMillis: Long = 60000 // Default 1 min
    private var timer: CountDownTimer? = null
    private var isRunning = false
    private var timeLeftInMillis: Long = 0 // Time remaining when paused

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temporizador)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.timerTextView)
        val editText = findViewById<EditText>(R.id.timeInput)
        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

        startButton.setOnClickListener {
            if (!isRunning) {
                val inputTime = editText.text.toString().toLongOrNull()
                timeInMillis = inputTime?.times(1000) ?: 60000 // Default to 1 min if no input
                startTimer(timeInMillis, textView)
            } else {
                resumeTimer(textView)
            }
        }

        stopButton.setOnClickListener {
            pauseTimer()
        }

        resetButton.setOnClickListener {
            resetTimer(textView)
        }
    }

    private fun startTimer(timeInMillis: Long, textView: TextView) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                textView.text = "Seconds remaining: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                textView.text = "Done!"
            }
        }.start()
        isRunning = true
    }

    private fun pauseTimer() {
        timer?.cancel()
        isRunning = false
    }

    private fun resumeTimer(textView: TextView) {
        startTimer(timeLeftInMillis, textView)
    }

    private fun resetTimer(textView: TextView) {
        pauseTimer()
        timeLeftInMillis = 0
        textView.text = "Timer"
    }
}
