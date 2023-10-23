package com.example.projectuts_anmp

import com.example.projectuts_anmp.MenuAdapter
import com.example.projectuts_anmp.MenuViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MenuFragment : Fragment() {
    private lateinit var viewModel: com.example.projectuts_anmp.MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(com.example.projectuts_anmp.MenuViewModel::class.java)

        // Inisialisasi RecyclerView dan adapter untuk "appetizers" dan "rice and noodles"
        val appetizersRecyclerView = view.findViewById<RecyclerView>(R.id.appetizerRecyclerView)
        val riceAndNoodlesRecyclerView = view.findViewById<RecyclerView>(R.id.riceNoodlesRecyclerView)
        val appetizersAdapter =
            com.example.projectuts_anmp.MenuAdapter(requireContext(), mutableListOf())
        val riceAndNoodlesAdapter =
            com.example.projectuts_anmp.MenuAdapter(requireContext(), mutableListOf())

        // Set adapter ke RecyclerView
        appetizersRecyclerView.adapter = appetizersAdapter
        riceAndNoodlesRecyclerView.adapter = riceAndNoodlesAdapter

        // Mengamati LiveData untuk "appetizers" dan "rice and noodles"
        viewModel.appetizersLiveData.observe(viewLifecycleOwner, Observer { appetizers ->
            appetizersAdapter.updateMenuItems(appetizers)
        })

        viewModel.riceAndNoodlesLiveData.observe(viewLifecycleOwner, Observer { riceAndNoodles ->
            riceAndNoodlesAdapter.updateMenuItems(riceAndNoodles)
        })

        // Refresh data
        viewModel.refreshMenuData()
    }
}
