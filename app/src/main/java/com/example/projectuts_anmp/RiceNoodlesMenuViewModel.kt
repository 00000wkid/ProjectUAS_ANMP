package com.example.projectuts_anmp

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

@SuppressLint("Range")
class RiceNoodlesMenuViewModel(application: Application) : AndroidViewModel(application) {
    val riceNoodlesMenu: LiveData<List<MenuItem>>

    init {
        val menuList: MutableList<MenuItem> = mutableListOf()
        val dbHelper = DBHelper(getApplication(), null)
        val cursor = dbHelper.getAppetizer()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL))
                    val name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl))

                    val price = cursor.getDouble(cursor.getColumnIndex(DBHelper.PRICE_COL))
                    val desc = cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL))
                    val image = cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_COL))

                    // Create a MenuItem instance and add it to the list
                    val menuItem = MenuItem(id, name, price, image, desc)
                    menuList.add(menuItem)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        riceNoodlesMenu = MutableLiveData()
        val json = application.resources.openRawResource(R.raw.menu_data).bufferedReader().use { it.readText() }
        val gson = Gson()
        val menuData = gson.fromJson(json, MenuData::class.java)
        Log.d("dfjkviewmodel", "isi view model: $menuData")

        riceNoodlesMenu.postValue(menuData.riceAndNoodles)
    }
}
