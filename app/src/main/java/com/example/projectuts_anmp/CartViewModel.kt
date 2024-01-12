package com.example.projectuts_anmp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class CartViewModel(private val context: Context) : ViewModel() {
    private val cartItems: MutableLiveData<MutableList<CartItem>> = MutableLiveData(mutableListOf())

    fun addToCart(menuItem: MenuItem, quantity: Int) {
        val dbHelper = DBHelper(context, null)
        dbHelper.addCart(menuItem.name,menuItem.description,menuItem.price,quantity,menuItem.category,menuItem.imageUrl)
        Log.d("dfjkff", "isi: $menuItem")
        Log.d("CartViewModel", "Jumlah item dalam cartViewModel: ${cartItems.value?.size}")

    }

    fun getCartItems(): MutableList<CartItem> {
        val dbHelper = DBHelper(context, null)
        val cursor = dbHelper.getCart()
        val menuList: MutableList<CartItem> = mutableListOf()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL))
                    val name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl))
                    val price = cursor.getDouble(cursor.getColumnIndex(DBHelper.PRICE_COL))
                    val desc = cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL))
                    val image = cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_COL))
                    val qty = cursor.getInt(cursor.getColumnIndex(DBHelper.QTY_COL))
                    val category = cursor.getString(cursor.getColumnIndex(DBHelper.CATEGORY_COL))

                    val menuItem = MenuItem(id, name, price, image, desc, qty, category)
                    val cartItem = CartItem(menuItem, qty)
                    menuList.add(cartItem)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        Log.d("CartViewModel", "Jumlah item dalam CartViewModel: ${menuList.size}")

          return menuList
    }

    fun removeItemFromCart(position: Int, NAME: String) {
        val currentCartItems = cartItems.value?.toMutableList()
        if (currentCartItems != null && currentCartItems.size > position) {
            currentCartItems.removeAt(position)
//            cartItems.value = currentCartItems
        }
        Log.d("position", "position: $position")

        //delete from database
        val dbHelper = DBHelper(context, null)
//        val cartItem = cartItems.value?.get(position)

        dbHelper.deleteCart(NAME)

        //delete from list


    }

}
