package com.example.projectuts_anmp

// SessionManager.kt
import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

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

}
