package com.example.pesquisaeleitoral

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerEstado: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurando os itens do Spinner de prefeito
        val spinnerPrefeito = findViewById<Spinner>(R.id.spinnerPrefeito)
        val candidatosPrefeito = arrayOf("Flavio Gabriel", "Diego Meireles", "João Faria", "Italo Diniz")
        val adapterPrefeito = ArrayAdapter(this, android.R.layout.simple_spinner_item, candidatosPrefeito)
        adapterPrefeito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPrefeito.adapter = adapterPrefeito

        // Configurando os itens do Spinner de vereador
        val spinnerVereador = findViewById<Spinner>(R.id.spinnerVereador)
        val candidatosVereador = arrayOf("Fernado do Leite", "Carlos do Povão", "Kleito Cabelereiro", "Doutora Maria")
        val adapterVereador = ArrayAdapter(this, android.R.layout.simple_spinner_item, candidatosVereador)
        adapterVereador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerVereador.adapter = adapterVereador

        // Inicialize o Spinner
        spinnerEstado = findViewById(R.id.spinnerEstado)
        // Lista de estados brasileiros
        val estados = arrayOf(
            "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
            "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão",
            "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará",
            "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
            "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia",
            "Roraima", "Santa Catarina", "São Paulo", "Sergipe",
            "Tocantins"
        )

        // Cria um ArrayAdapter usando o layout padrão de spinner e a lista de estados
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Configura o adapter no spinner
        spinnerEstado.adapter = adapter

        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)
        buttonEnviar.setOnClickListener {
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val titulo = findViewById<EditText>(R.id.editTextTituloEleitor).text.toString()
            val zona = findViewById<EditText>(R.id.editTextZona).text.toString()
            val secao = findViewById<EditText>(R.id.editTextSecao).text.toString()
            val cidade = findViewById<EditText>(R.id.editTextCidade).text.toString()
            val estado = spinnerEstado.selectedItem.toString()
            val prefeito = spinnerPrefeito.selectedItem.toString()
            val vereador = spinnerVereador.selectedItem.toString()

            // Coletando partidos selecionados
            val partidosFavoritos = mutableListOf<String>()
            if (findViewById<CheckBox>(R.id.checkBoxPartido1).isChecked) partidosFavoritos.add("Partido das Pessoas Unidas - PPU")
            if (findViewById<CheckBox>(R.id.checkBoxPartido2).isChecked) partidosFavoritos.add("Partido dos Empresarios - PE")
            if (findViewById<CheckBox>(R.id.checkBoxPartido3).isChecked) partidosFavoritos.add("Partido de Pessoa a Pessoa - PPP")
            if (findViewById<CheckBox>(R.id.checkBoxPartido4).isChecked) partidosFavoritos.add("Partido Altamente Totalmente Organizado - PATO")
            if (findViewById<CheckBox>(R.id.checkBoxPartido5).isChecked) partidosFavoritos.add("Partido Favorito - PF")
            if (findViewById<CheckBox>(R.id.checkBoxPartido6).isChecked) partidosFavoritos.add("Partido Rural Organizado - PRO")

            // Verificar se todos os campos obrigatórios estão preenchidos
            if (nome.isEmpty() || titulo.isEmpty() || zona.isEmpty() || secao.isEmpty() || cidade.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Formatando os partidos favoritos
            val partidosFormatados = if (partidosFavoritos.isNotEmpty()) partidosFavoritos.joinToString(", ") else "Nenhum"

            // Enviar dados para a outra atividade
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra("NOME", nome)
                putExtra("TITULO", titulo)
                putExtra("ZONA", zona)
                putExtra("SECAO", secao)
                putExtra("CIDADE", cidade)
                putExtra("ESTADO", estado)
                putExtra("VEREADOR", vereador)
                putExtra("PREFEITO", prefeito)
                putExtra("PARTIDOS", partidosFormatados)
            }

            startActivity(intent)
        }
} }