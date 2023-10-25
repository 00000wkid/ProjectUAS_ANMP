package com.example.projectuts_anmp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orders: List<OrderItem>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Inisialisasi elemen tampilan item pesanan di sini
        val tableNumberTextView: TextView = itemView.findViewById(R.id.txtTableNumber)
        val priceTextView: TextView = itemView.findViewById(R.id.txtdeskripsi)
        val durationTextView: TextView = itemView.findViewById(R.id.txtDurasiOrder)
        val descriptionTextView: TextView = itemView.findViewById(R.id.txtdeskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        // Setel data pesanan ke elemen tampilan
        holder.tableNumberTextView.text = "Table ${order.tableNumber}"
        holder.priceTextView.text = "Price: ${order.price}"
        holder.durationTextView.text = "Duration: ${order.duration}"
        holder.descriptionTextView.text = "Description: ${order.description}"
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}
