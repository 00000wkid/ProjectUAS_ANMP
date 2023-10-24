package com.example.projectuts_anmp

// SessionManager.kt
import android.content.Context
import android.content.SharedPreferences
import com.example.projectuts_anmp.MenuItem  // Sesuaikan dengan paket dan nama kelas yang benar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    private val sharedPreferences = context.getSharedPreferences("CartSession", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun saveTableNumber(tableNumber: Int) {
        val editor = sharedPref.edit()
        editor.putInt("tableNumber", tableNumber)
        editor.apply()
    }

    fun getTableNumber(): Int {
        return sharedPref.getInt("tableNumber", 0) // 0 adalah nilai default jika data tidak tersedia
    }
    fun clearTableNumber() {
        val editor = sharedPref.edit()
        editor.remove("tableNumber")
        editor.apply()
    }
//    fun addToCart(menuItem: MenuItem, quantity: Int) {
//        val cartItems = getCart()
//        val existingItem = cartItems.find { it.id == menuItem.id }
//
//        if (existingItem != null) {
//            existingItem.quantity += quantity
//        } else {
//            menuItem.quantity = quantity
//            cartItems.add(menuItem)
//        }
//
//        saveCart(cartItems)
//    }
//
//    fun getCart(): MutableList<MenuItem> {
//        val json = sharedPreferences.getString("cartItems", "")
//        val type = object : TypeToken<MutableList<MenuItem>>() {}.type
//        return Gson().fromJson(json, type) ?: mutableListOf()
//    }
//
//    fun saveCart(cartItems: MutableList<MenuItem>) {
//        val json = Gson().toJson(cartItems)
//        editor.putString("cartItems", json)
//        editor.apply()
//    }


}
