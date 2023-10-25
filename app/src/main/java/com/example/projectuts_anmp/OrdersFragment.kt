package com.example.projectuts_anmp

import OrderViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.projectuts_anmp.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment() {
    private lateinit var binding: FragmentOrdersBinding
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inisialisasi ViewModel
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        // Load data pesanan (misalnya, dari sumber data seperti JSON)
        orderViewModel.loadOrder()

        // Inisialisasi RecyclerView
        val recyclerView = binding.ordersRecyclerView
        val orderData = orderViewModel.orders.value
        val orderItems = orderData?.orders ?: emptyList()

        val orderAdapter = OrderAdapter(orderItems)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = orderAdapter

        return view
    }

}