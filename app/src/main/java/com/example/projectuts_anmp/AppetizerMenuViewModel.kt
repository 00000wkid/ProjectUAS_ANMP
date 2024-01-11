package com.example.projectuts_anmp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AppetizerMenuViewModel(application: Application) : AndroidViewModel(application) {
    val appetizerMenu: LiveData<List<MenuItem>>

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
                    val qty = cursor.getInt(cursor.getColumnIndex(DBHelper.QTY_COL))
                    val category = cursor.getString(cursor.getColumnIndex(DBHelper.CATEGORY_COL))
                    // Create a MenuItem instance and add it to the list
                    val menuItem = MenuItem(id, name, price, image, desc,qty,category)
                    menuList.add(menuItem)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        appetizerMenu = MutableLiveData()
//        val json = application.resources.openRawResource(R.raw.menu_data).bufferedReader().use { it.readText() }
//        val gson = Gson()
//        val menuData = gson.fromJson(json, MenuData::class.java)
//        appetizerMenu.postValue(menuData.appetizers)

        appetizerMenu.postValue(menuList)

    }
}