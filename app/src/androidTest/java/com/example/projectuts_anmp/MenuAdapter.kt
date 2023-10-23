package com.example.projectuts_anmp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val context: Context, private val menuItems: List<MenuItem>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.itemName.text = menuItem.name
        holder.itemPrice.text = "Price: $${menuItem.price}"

        // Set gambar menu (itemImage) sesuai dengan gambar yang sesuai dengan menuItem
        // Anda dapat menggunakan Glide atau library lainnya untuk mengatur gambar.

        // Contoh menggunakan Glide:
        // Glide.with(context)
        //     .load(menuItem.imageUrl)
        //     .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    fun updateMenuItems(newMenuItems: List<MenuItem>) {

        notifyDataSetChanged()
    }
}
