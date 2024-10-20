package com.example.tarefarecyclerview;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val estadoList = obterEstados()
        val adapter = EstadoAdapter(estadoList) { estado ->
            val intent = Intent(this, DetalhesEstadoActivity::class.java)
            intent.putExtra("estado", estado)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

    private fun obterEstados(): List<Estado> {
        return listOf(
            Estado(R.drawable.ac, "Acre", "Rio Branco", 830018, "Norte"),
            Estado(R.drawable.al, "Alagoas", "Maceió", 3127683, "Nordeste"),
            Estado(R.drawable.ap, "Amapá", "Macapá", 733759, "Norte"),
            Estado(R.drawable.am, "Amazonas", "Manaus", 3941613, "Norte"),
            Estado(R.drawable.ba, "Bahia", "Salvador", 14141626	, "Nordeste"),
            Estado(R.drawable.ce, "Ceará", "Fortaleza", 8794957, "Nordeste"),
            Estado(R.drawable.df, "Distrito Federal", "Brasília", 2817381	, "Centro-Oeste"),
            Estado(R.drawable.es, "Espírito Santo", "Vitória", 3833712, "Sudeste"),
            Estado(R.drawable.go, "Goiás", "Goiânia", 7056495, "Centro-Oeste"),
            Estado(R.drawable.ma, "Maranhão", "São Luís", 6775805, "Nordeste"),
            Estado(R.drawable.mt, "Mato Grosso", "Cuiabá", 3658649, "Centro-Oeste"),
            Estado(R.drawable.ms, "Mato Grosso do Sul", "Campo Grande", 2757013	, "Centro-Oeste"),
            Estado(R.drawable.mg, "Minas Gerais", "Belo Horizonte", 20538718	, "Sudeste"),
            Estado(R.drawable.pa, "Pará", "Belém", 8121025, "Norte"),
            Estado(R.drawable.pb, "Paraíba", "João Pessoa", 3974687, "Nordeste"),
            Estado(R.drawable.pr, "Paraná", "Curitiba", 11444380, "Sul"),
            Estado(R.drawable.pe, "Pernambuco", "Recife", 9058931, "Nordeste"),
            Estado(R.drawable.pi, "Piauí", "Teresina", 3271199, "Nordeste"),
            Estado(R.drawable.rj, "Rio de Janeiro", "Rio de Janeiro", 16054524, "Sudeste"),
            Estado(R.drawable.rn, "Rio Grande do Norte", "Natal", 3302729, "Nordeste"),
            Estado(R.drawable.rs, "Rio Grande do Sul", "Porto Alegre", 10882965, "Sul"),
            Estado(R.drawable.ro, "Rondônia", "Porto Velho", 1581196, "Norte"),
            Estado(R.drawable.rr, "Roraima", "Boa Vista", 636707, "Norte"),
            Estado(R.drawable.sc, "Santa Catarina", "Florianópolis", 7610361, "Sul"),
            Estado(R.drawable.sp, "São Paulo", "São Paulo", 44411238, "Sudeste"),
            Estado(R.drawable.se, "Sergipe", "Aracaju", 2209558, "Nordeste"),
            Estado(R.drawable.to, "Tocantins", "Palmas", 1511460	, "Norte")
        )
    }
}
