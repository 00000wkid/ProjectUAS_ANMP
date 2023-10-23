package com.example.projectuts_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson


class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
        val jsonRes = resources.openRawResource(R.raw.menu_data)
        val jsonSize = jsonRes.available()
        val buffer = ByteArray(jsonSize)
        jsonRes.read(buffer)
        jsonRes.close()
        val jsonString = String(buffer, Charsets.UTF_8)

// Konversi JSON ke objek Kotlin menggunakan Gson (Anda perlu menambahkan dependensi Gson)
        val gson = Gson()
        val menuData = gson.fromJson(jsonString, MenuData::class.java)
    }


}