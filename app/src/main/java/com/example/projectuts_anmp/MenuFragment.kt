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

        //  ViewModel
        appetizerMenuViewModel = ViewModelProvider(this).get(AppetizerMenuViewModel::class.java)
        riceNoodlesMenuViewModel = ViewModelProvider(this).get(RiceNoodlesMenuViewModel::class.java)

        //  RecyclerView untuk "Appetizers"
        val appetizerRecyclerView = binding.appetizerRecyclerView
        val appetizerMenuAdapter = MenuAdapter()
        appetizerRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        appetizerRecyclerView.adapter = appetizerMenuAdapter

        // Observe LiveData ViewModel "Appetizers"
        appetizerMenuViewModel.appetizerMenu.observe(viewLifecycleOwner) { menuItems ->
            appetizerMenuAdapter.submitList(menuItems)
        }

        //  RecyclerView "Rice and Noodles"
        val riceNoodlesRecyclerView = binding.riceNoodlesRecyclerView
        val riceNoodlesMenuAdapter = MenuAdapter()
        riceNoodlesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        riceNoodlesRecyclerView.adapter = riceNoodlesMenuAdapter

        // Observe LiveData  ViewModel  "Rice and Noodles"
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

