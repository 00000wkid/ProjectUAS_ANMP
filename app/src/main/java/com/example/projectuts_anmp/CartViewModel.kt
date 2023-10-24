package com.example.projectuts_anmp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CartViewModel : ViewModel() {
    private val cartItems: MutableLiveData<MutableList<CartItem>> = MutableLiveData(mutableListOf())

    fun addToCart(menuItem: MenuItem, quantity: Int) {
        val currentCartItems = cartItems.value ?: mutableListOf()

        val existingCartItem = currentCartItems.find { it.menuItem.name == menuItem.name }

        if (existingCartItem != null) {
            val updatedQuantity = existingCartItem.quantity + quantity
            existingCartItem.quantity = updatedQuantity
        } else {
            val newCartItem = CartItem(menuItem, quantity)
            currentCartItems.add(newCartItem)
        }
        Log.d("CartViewModel", "Jumlah item dalam cartViewModel: ${cartItems.value?.size}")

        cartItems.value = currentCartItems
    }

    fun getCartItems(): LiveData<MutableList<CartItem>> {
        Log.d("CartViewModel", "Jumlah item dalam CartViewModel: ${cartItems.value?.size ?: 0}")

        return cartItems

    }
    fun removeItemFromCart(position: Int) {
        val currentCartItems = cartItems.value?.toMutableList()
        if (currentCartItems != null && currentCartItems.size > position) {
            currentCartItems.removeAt(position)
//            cartItems.value = currentCartItems
        }
    }

}
