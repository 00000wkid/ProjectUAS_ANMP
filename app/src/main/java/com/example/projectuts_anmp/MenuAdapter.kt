package com.example.projectuts_anmp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuts_anmp.R
import com.squareup.picasso.Picasso

class MenuAdapter : ListAdapter<MenuItem, MenuAdapter.MenuViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = getItem(position)

        holder.bind(menuItem)

        holder.itemView.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToMenuDetailFragment(menuItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.menuNameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.menuPriceTextView)
        private val menuImageView: ImageView = itemView.findViewById(R.id.menuImageView)

        fun bind(menuItem: MenuItem) {
            nameTextView.text = menuItem.name
            priceTextView.text = String.format("$%.2f", menuItem.price)
            Picasso.get().load(menuItem.imageUrl).into(menuImageView)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
