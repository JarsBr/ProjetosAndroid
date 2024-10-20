package com.example.pesquisaeleitoral

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome = intent.getStringExtra("NOME")
        val titulo = intent.getStringExtra("TITULO")
        val zona = intent.getStringExtra("ZONA")
        val secao = intent.getStringExtra("SECAO")
        val cidade = intent.getStringExtra("CIDADE")
        val estado = intent.getStringExtra("ESTADO")
        val vereador = intent.getStringExtra("VEREADOR")
        val prefeito = intent.getStringExtra("PREFEITO")
        val partidos = intent.getStringExtra("PARTIDOS")

        val resultadoTextView = findViewById<TextView>(R.id.textViewResultado)
        resultadoTextView.text = """
            Nome: $nome
            Título de Eleitor: $titulo
            Zona: $zona
            Seção: $secao
            Cidade: $cidade
            Estado: $estado
            Vereador: $vereador
            Prefeito: $prefeito
            Partidos Favoritos: $partidos
        """.trimIndent()
    }
}