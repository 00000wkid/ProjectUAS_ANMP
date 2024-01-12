package com.example.projectuts_anmp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView


class CartAdapter(private val context: Context, private var items: List<CartItem>, private val cartViewModel: CartViewModel) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.tablenumber)
        val itemPrice: TextView = itemView.findViewById(R.id.cartItemPriceTextView)
        val itemQuantity: TextView = itemView.findViewById(R.id.txtTableNumber)
        val deleteCartItemButton: Button = itemView.findViewById(R.id.deleteCartItemButton)
//        val image: TextView = itemView.findViewById(R.id.cartItemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = items[position]


//        Picasso.get().load(cartItem.menuItem.imageUrl).into(holder.image)
        holder.itemName.text = cartItem.menuItem.name
        holder.itemPrice.text = "Harga: ${cartItem.menuItem.price}"
        holder.itemQuantity.text = "Jumlah: ${cartItem.quantity}"
        Log.d("CartAdapter", "Jumlah item dalam cartAdapter: ${holder.itemName.text} ${holder.itemPrice} ${holder.itemQuantity.text} ")

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
        holder.deleteCartItemButton.setOnClickListener {
            showDeleteConfirmationDialog(holder.adapterPosition, cartItem.menuItem.name)
        }
    }




    override fun getItemCount(): Int {
        return items.size
    }
    fun setItems(items: List<CartItem>) {
        this.items = ArrayList(items)
    }
    fun updateData(items: List<CartItem>) {
        //clear item
        this.items = ArrayList(items)
        notifyDataSetChanged()

    }
    fun removeItem(position: Int) {
//        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    private fun showDeleteConfirmationDialog(position: Int, Name: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Hapus Item")
        builder.setMessage("Apakah Anda yakin ingin menghapus item dari keranjang?")
//        Log.d("CartAdapter", "Jumlah item dalam cartAdapter: ${id} ")
        builder.setPositiveButton("Ya") { _, _ ->
            removeItem(position)
            cartViewModel.removeItemFromCart(position,Name)
            updateData(cartViewModel.getCartItems())
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->

            dialog.dismiss()
        }
        builder.create().show()
    }
}
