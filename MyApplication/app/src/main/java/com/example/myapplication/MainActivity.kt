package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val beerBrands = mapOf(
        "Heineken" to listOf("Heineken Original", "Heineken Light", "Heineken 0.0"),
        "Budweiser" to listOf("Budweiser Lager", "Bud Light", "Budweiser Zero"),
        "Corona" to listOf("Corona Extra", "Corona Light", "Corona Premier"),
        "Skol" to listOf("Skol Pilsen", "Skol Hops", "Skol Beats"),
        "Brahma" to listOf("Brahma Pilsen", "Brahma Duplo Malte", "Brahma Extra")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_linear)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.spinner1)
        val button: Button = findViewById(R.id.button1)
        val textView: TextView = findViewById(R.id.textView2)

        // Lista de cervejas
        val beerList = listOf("Heineken", "Budweiser", "Corona", "Skol", "Brahma")

        //Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, beerList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Botão "Buscar"
        button.setOnClickListener {
            val selectedBrand = spinner.selectedItem.toString()

            val beers = beerBrands[selectedBrand]?.joinToString("\n") ?: "Nenhuma cerveja encontrada"

            textView.text = "Cervejas da marca $selectedBrand:\n$beers"
        }
    }
}