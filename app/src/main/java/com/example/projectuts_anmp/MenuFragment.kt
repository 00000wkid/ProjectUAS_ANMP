package com.example.projectuts_anmp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuts_anmp.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var appetizerMenuViewModel: AppetizerMenuViewModel
    private lateinit var riceNoodlesMenuViewModel: RiceNoodlesMenuViewModel

    private lateinit var menuDatabase: MenuDatabase
    private lateinit var menuDao: MenuDao

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //  Database
        val dbHelper= DBHelper(this.requireContext(),null)
        val cursor = dbHelper.getAppetizer()
        val count = cursor?.count
        Log.d("dfjkfff", "count: $count")
        //insert to list
        val menuList: MutableList<MenuItem> = mutableListOf()

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL))
                    val name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl))

                    val price = cursor.getDouble(cursor.getColumnIndex(DBHelper.PRICE_COL))
                    val desc = cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL))
                    val image = cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_COL))

                    // Create a MenuItem instance and add it to the list
                    val menuItem = MenuItem(id, name, price,image,desc)
                    menuList.add(menuItem)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        Log.d("dfjkdatabase", "isi: $menuList")








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

