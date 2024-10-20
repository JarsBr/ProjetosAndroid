package com.example.tarefarecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstadoAdapter(
    private val estadoList: List<Estado>,
    private val listener: (Estado) -> Unit
) : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estado, parent, false)
        return EstadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val estado = estadoList[position]
        holder.bind(estado, listener)
    }

    override fun getItemCount() = estadoList.size

    class EstadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivBandeira: ImageView = itemView.findViewById(R.id.ivBandeira)
        private val tvNomeEstado: TextView = itemView.findViewById(R.id.tvNomeEstado)

        fun bind(estado: Estado, listener: (Estado) -> Unit) {
            ivBandeira.setImageResource(estado.bandeiraResId)
            tvNomeEstado.text = estado.nome
            itemView.setOnClickListener { listener(estado) }
        }
    }
}
