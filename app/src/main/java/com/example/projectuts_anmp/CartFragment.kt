package com.example.projectuts_anmp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuts_anmp.databinding.FragmentCartBinding
import java.text.DecimalFormat

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
        //get cartViewModel
        val cartViewModel = CartViewModel(requireContext())
        val cartRecyclerView = binding.cartRecyclerView

        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartAdapter = CartAdapter(
            requireContext(),
            emptyList(),cartViewModel
        )
        //get total price
        val totalPriceTextView = binding.totalPriceTextView
        val taxTextView = binding.taxTextView
        cartRecyclerView.adapter = cartAdapter
        Log.d("CartFragment", "Jumlah item dalam cartFragment: ");
        //get cart item
        val cartItems = cartViewModel.getCartItems()
        cartAdapter.setItems(cartItems)
        cartAdapter.notifyDataSetChanged()
        //get total price
        val totalPrice = calculateTotalPrice(cartItems)
        val tax = totalPrice * 0.1
        val df = DecimalFormat("#.##")
        totalPriceTextView.text = "Rp. ${df.format(totalPrice)}"
        taxTextView.text = "Rp. ${df.format(tax)}"

        //checkoutbutton clicked toast (berhasil)
        val checkoutButton = binding.checkoutButton
        checkoutButton.setOnClickListener {
            Toast.makeText(requireContext(), "Berhasil!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
    private fun calculateTotalPrice(items: List<CartItem>): Double {
        var total = 0.0
        for (item in items) {
            total += item.menuItem.price * item.quantity
        }
        return total
    }
}