package com.example.tarefarecyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesEstadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_estado)

        val ivBandeira: ImageView = findViewById(R.id.ivBandeiraDetalhe)
        val tvNome: TextView = findViewById(R.id.tvNomeDetalhe)
        val tvCapital: TextView = findViewById(R.id.tvCapitalDetalhe)
        val tvPopulacao: TextView = findViewById(R.id.tvPopulacaoDetalhe)
        val tvRegiao: TextView = findViewById(R.id.tvRegiaoDetalhe)

        val estado = intent.getSerializableExtra("estado") as? Estado
        estado?.let {
            ivBandeira.setImageResource(it.bandeiraResId)
            tvNome.text = "Estado: ${it.nome}"
            tvCapital.text = "Capital: ${it.capital}"
            tvPopulacao.text = "População: ${it.populacao}"
            tvRegiao.text = "Região: ${it.regiao}"
        }
    }
}
