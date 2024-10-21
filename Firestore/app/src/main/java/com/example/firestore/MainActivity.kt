package com.example.firestore

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCapital: EditText
    private lateinit var editTextPais: EditText
    private lateinit var editTextSigla: EditText
    private lateinit var textViewAviso: TextView
    private lateinit var buttonCriar: Button
    private lateinit var buttonLer: Button
    private lateinit var buttonAtualizar: Button
    private lateinit var buttonDeletar: Button
    private lateinit var db: FirebaseFirestore
    private var cidade: String = ""
    private var pais: String = ""
    private var sigla: String = ""
    private lateinit var paises: Paises

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = FirebaseFirestore.getInstance()
        editTextCapital = findViewById(R.id.editTextCapital)
        editTextPais = findViewById(R.id.editTextPais)
        editTextSigla = findViewById(R.id.editTextSigla)
        textViewAviso = findViewById(R.id.textViewAviso)
        buttonCriar = findViewById(R.id.buttonCriar)
        buttonLer = findViewById(R.id.buttonLer)
        buttonAtualizar = findViewById(R.id.buttonAtualizar)
        buttonDeletar = findViewById(R.id.buttonDeletar)
        buttonCriar.setOnClickListener { criar() }
        buttonLer.setOnClickListener { ler() }
        buttonAtualizar.setOnClickListener { atualizar() }
        buttonDeletar.setOnClickListener { deletar() }
    }

    private fun criar() {
        val capitais = db.collection("paises")
        cidade = editTextCapital.text.toString()
        pais = editTextPais.text.toString()
        sigla = editTextSigla.text.toString()
        // Verifica se os campos estão vazios e interrompe se necessário
        when {
            TextUtils.isEmpty(cidade) -> {
                editTextCapital.error = "Digite o nome da cidade"
                return
            }

            TextUtils.isEmpty(pais) -> {
                editTextPais.error = "Digite o nome do país"
                return
            }

            TextUtils.isEmpty(sigla) -> {
                editTextSigla.error = "Digite a sigla do país"
                return
            }
        }
        paises = Paises(cidade, pais, sigla)
        // Insere no Firestore e trata o sucesso e a falha
        capitais.document(sigla).set(paises)
            .addOnSuccessListener {
                // Se a inserção for bem-sucedida, limpa os campos e exibe uma mensagem
                editTextCapital.text.clear()
                editTextPais.text.clear()
                editTextSigla.text.clear()
                textViewAviso.text = "Inserido com sucesso!"
            }
            .addOnFailureListener { e ->
                // Se houver falha, exibe uma mensagem de erro no log e na interface
                Log.w("Firestore", "Erro ao inserir documento", e)
                textViewAviso.text = "Erro ao inserir, tente novamente."
            }
    }

    private fun ler() {
        sigla = editTextSigla.text.toString()
        val docRef = db.collection("paises").document(sigla)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    val c = document.toObject(Paises::class.java)
                    c?.let {
                        editTextCapital.setText(it.capital)
                        editTextPais.setText(it.pais)
                        editTextSigla.setText(it.sigla)
                        textViewAviso.text = "Encontrado"
                    }
                } else {
                    textViewAviso.text = "Não encontrado"
                }
            } else {
                Log.d("TAG", "Falha ao buscar documento",
                    task.exception)
            }
        }
    }

    private fun ler2() {
        cidade = editTextCapital.text.toString()
        db.collection("paises")
            .whereEqualTo("capital", cidade)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val c = document.
                        toObject(Paises::class.java)
                        editTextCapital.setText(c.capital)
                        editTextPais.setText(c.pais)
                        editTextSigla.setText(c.sigla)
                    }
                } else {
                    Log.d("TAG", "Erro ao buscar documentos",
                        task.exception)
                }
            }
    }

    private fun atualizar() {
        sigla = editTextSigla.text.toString()
        db.collection("paises").document(sigla)
            .update(
                "capital", editTextCapital.text.toString(),
                "pais", editTextPais.text.toString()
            )
            .addOnSuccessListener {
                textViewAviso.text = "Atualizado!"
                ler2()
            }
            .addOnFailureListener {
                Log.d("TAG", "Falha ao atualizar", it)
            }
    }

    private fun deletar() {
        sigla = editTextSigla.text.toString()
        db.collection("paises").document(sigla)
            .delete()
            .addOnSuccessListener {
                textViewAviso.text = "Deletado!"
            }
            .addOnFailureListener {
                Log.w("TAG", "Erro ao deletar!", it)
            }
    }
}