//package com.example.projectuts_anmp
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.google.gson.Gson
//
//class MenuViewModel : ViewModel() {
//    private val _appetizersLiveData = MutableLiveData<List<MenuItem>>()
//    val appetizersLiveData: LiveData<List<MenuItem>>
//        get() = _appetizersLiveData
//
//    private val _riceAndNoodlesLiveData = MutableLiveData<List<MenuItem>>()
//    val riceAndNoodlesLiveData: LiveData<List<MenuItem>>
//        get() = _riceAndNoodlesLiveData
//
//    fun refreshMenuData() {
//
//        val appetizersData = listOf(
//            MenuItem("Appetizer 1", 9.99, "url1"),
//            MenuItem("Appetizer 2", 8.49, "url2"),
//            MenuItem("Appetizer 3", 7.99, "url3")
//        )
//        _appetizersLiveData.value = appetizersData
//
//        // Contoh: Mengisikan data "rice and noodles" dengan beberapa item menu
//        val riceAndNoodlesData = listOf(
//            MenuItem("Rice and Noodles 1", 11.99, "url4"),
//            MenuItem("Rice and Noodles 2", 10.49, "url5"),
//            MenuItem("Rice and Noodles 3", 12.99, "url6")
//        )
//        _riceAndNoodlesLiveData.value = riceAndNoodlesData
//    }
//
//    // Fungsi untuk mengambil data "appetizers" dari sumber data
//    private fun loadAppetizersData(context: Context): List<MenuItem> {
//        try {
//            val inputStream = context.resources.openRawResource(R.raw.menu_data)
//            val size = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            val jsonString = String(buffer, Charsets.UTF_8)
//
//            // Parsing JSON data (Anda perlu menambahkan kode parsing JSON sesuai dengan struktur file JSON Anda)
//            val gson = Gson()
//            val menuData = gson.fromJson(jsonString, MenuData::class.java)
//
//            // Anda dapat mengakses data "appetizers" dari objek menuData
//            return menuData.appetizers
//        } catch (e: Exception) {
//            // Handle kesalahan saat membaca atau memproses file JSON
//            e.printStackTrace()
//            return emptyList()
//        }
//    }
//
//    private fun loadRiceAndNoodlesData(context: Context): List<MenuItem> {
//        try {
//            val inputStream = context.resources.openRawResource(R.raw.menu_data)
//            val size = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            val jsonString = String(buffer, Charsets.UTF_8)
//
//            // Parsing JSON data (Anda perlu menambahkan kode parsing JSON sesuai dengan struktur file JSON Anda)
//            val gson = Gson()
//            val menuData = gson.fromJson(jsonString, MenuData::class.java)
//
//            // Anda dapat mengakses data "rice and noodles" dari objek menuData
//            return menuData.riceAndNoodles
//        } catch (e: Exception) {
//            // Handle kesalahan saat membaca atau memproses file JSON
//            e.printStackTrace()
//            return emptyList()
//        }
//    }
//
//
//}
