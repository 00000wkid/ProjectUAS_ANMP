package com.example.projectuts_anmp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

class AppetizerMenuViewModel(application: Application) : AndroidViewModel(application) {
    val appetizerMenu: LiveData<List<MenuItem>>

    init {
        appetizerMenu = MutableLiveData()
        val json = application.resources.openRawResource(R.raw.menu_data).bufferedReader().use { it.readText() }
        val gson = Gson()
        val menuData = gson.fromJson(json, MenuData::class.java)
        appetizerMenu.postValue(menuData.appetizers)
    }
}