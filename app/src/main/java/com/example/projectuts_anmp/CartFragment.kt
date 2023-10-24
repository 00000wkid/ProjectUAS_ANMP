package com.example.projectuts_anmp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuts_anmp.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        val cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        val cartRecyclerView = binding.cartRecyclerView

        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartAdapter = CartAdapter(
            requireContext(),
            emptyList(),cartViewModel
        )
        cartRecyclerView.adapter = cartAdapter
        Log.d("CartFragment", "Jumlah item dalam cartFragment: ")
        cartViewModel.getCartItems().observe(viewLifecycleOwner) { items ->
            Log.d("CartFragment", "Jumlah item dalam cartItems: ${items.size}")
            cartAdapter.setItems(items)
            cartAdapter.notifyDataSetChanged()
        }

        return view
    }
}
