package com.example.fragmento

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var buttonFragment1: Button
    private lateinit var buttonFragment2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonFragment1 = findViewById(R.id.buttonFragment1)
        buttonFragment2 = findViewById(R.id.buttonFragment2)
        // Mostrar o primeiro fragmento inicialmente
        replaceFragment(FirstFragment())
        // Alternar fragmentos ao clicar nos botões
        buttonFragment1.setOnClickListener {
            replaceFragment(FirstFragment())
        }
        buttonFragment2.setOnClickListener {
            replaceFragment(SecondFragment())
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

}