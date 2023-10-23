package com.example.projectuts_anmp

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuts_anmp.MenuAdapter
import com.example.projectuts_anmp.MenuData
import com.example.projectuts_anmp.MenuItem
import com.google.gson.Gson
import com.example.projectuts_anmp.databinding.FragmentMenuBinding
import java.io.InputStreamReader
import com.example.projectuts_anmp.R

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var appetizerMenuViewModel: AppetizerMenuViewModel
    private lateinit var riceNoodlesMenuViewModel: RiceNoodlesMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sessionManager = SessionManager(requireContext())
        val tableNumber = sessionManager.getTableNumber()



        binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inisialisasi ViewModel
        appetizerMenuViewModel = ViewModelProvider(this).get(AppetizerMenuViewModel::class.java)
        riceNoodlesMenuViewModel = ViewModelProvider(this).get(RiceNoodlesMenuViewModel::class.java)

        // Inisialisasi RecyclerView untuk "Appetizers"
        val appetizerRecyclerView = binding.appetizerRecyclerView
        val appetizerMenuAdapter = MenuAdapter()
        appetizerRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        appetizerRecyclerView.adapter = appetizerMenuAdapter

        // Observe LiveData dari ViewModel untuk "Appetizers"
        appetizerMenuViewModel.appetizerMenu.observe(viewLifecycleOwner) { menuItems ->
            appetizerMenuAdapter.submitList(menuItems)
        }

        // Inisialisasi RecyclerView untuk "Rice and Noodles"
        val riceNoodlesRecyclerView = binding.riceNoodlesRecyclerView
        val riceNoodlesMenuAdapter = MenuAdapter()
        riceNoodlesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        riceNoodlesRecyclerView.adapter = riceNoodlesMenuAdapter

        // Observe LiveData dari ViewModel untuk "Rice and Noodles"
        riceNoodlesMenuViewModel.riceNoodlesMenu.observe(viewLifecycleOwner) { menuItems ->
            riceNoodlesMenuAdapter.submitList(menuItems)
        }
        if(tableNumber != 0){
            val servingText = "Currently Serving Table $tableNumber"
            // Contoh menampilkan pesan pada TextView
            val tableNumberTextView = view.findViewById<TextView>(R.id.tableNumberTextView)
            tableNumberTextView.text = servingText
        }
        else{
            val servingText = "No Table to Serving"
            // Contoh menampilkan pesan pada TextView
            val tableNumberTextView = view.findViewById<TextView>(R.id.tableNumberTextView)
            tableNumberTextView.text = servingText
        }

        return view
    }
}

//class AppetizerMenuViewModel(application: Application) : AndroidViewModel(application) {
//    val appetizerMenu: LiveData<List<MenuItem>>//fix mungkin
//
//    init {
//        appetizerMenu = MutableLiveData() // Gunakan MutableLiveData yang benar
//        val inputStream = application.resources.openRawResource(R.raw.menu_data)
//        val json = InputStreamReader(inputStream).readText()
//        val gson = Gson()
//        val menuData = gson.fromJson(json, MenuData::class.java)
//        appetizerMenu.postValue(menuData.appetizers)
//    }
//}
//
//class RiceNoodlesMenuViewModel(application: Application) : AndroidViewModel(application) {
//    val riceNoodlesMenu: LiveData<List<MenuItem>>
//
//    init {
//        riceNoodlesMenu = MutableLiveData()
//        val inputStream = application.resources.openRawResource(R.raw.menu_data)
//        val json = InputStreamReader(inputStream).readText()
//        val gson = Gson()
//        val menuData = gson.fromJson(json, MenuData::class.java)
//        riceNoodlesMenu.postValue(menuData.riceAndNoodles)
//    }
//}
