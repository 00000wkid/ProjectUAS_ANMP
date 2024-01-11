package com.example.projectuts_anmp

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@SuppressLint("Range")
class RiceNoodlesMenuViewModel(application: Application) : AndroidViewModel(application) {
    val riceNoodlesMenu: LiveData<List<MenuItem>>

    init {
        //database
        val menuList: MutableList<MenuItem> = mutableListOf()
        val dbHelper = DBHelper(getApplication(), null)
        val cursor = dbHelper.getRiceNoodles()
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
                    // Create a MenuItem instance and add it to the list
                    val menuItem = MenuItem(id, name, price, image, desc,qty,category)
                    menuList.add(menuItem)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        
        riceNoodlesMenu = MutableLiveData()
        //json
//        val json = application.resources.openRawResource(R.raw.menu_data).bufferedReader().use { it.readText() }
//        val gson = Gson()
//        val menuData = gson.fromJson(json, MenuData::class.java)
//        Log.d("dfjkviewmodel", "isi view model: $menuData")

        //database
        Log.d("dfjkviewmodel", "isi database: $menuList.Appetizer")



        //json
//        riceNoodlesMenu.postValue(menuData.riceAndNoodles)

        //database
        riceNoodlesMenu.postValue(menuList)


    }
}
