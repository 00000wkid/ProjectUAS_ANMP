package com.example.projectuts_anmp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

class RiceNoodlesMenuViewModel(application: Application) : AndroidViewModel(application) {
    val riceNoodlesMenu: LiveData<List<MenuItem>>

    init {
        riceNoodlesMenu = MutableLiveData()
        val json = application.resources.openRawResource(R.raw.menu_data).bufferedReader().use { it.readText() }
        val gson = Gson()
        val menuData = gson.fromJson(json, MenuData::class.java)
        riceNoodlesMenu.postValue(menuData.riceAndNoodles)
    }
}