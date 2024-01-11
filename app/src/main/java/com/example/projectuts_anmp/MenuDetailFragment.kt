package com.example.projectuts_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projectuts_anmp.databinding.FragmentMenuDetailBinding
import com.squareup.picasso.Picasso


class MenuDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private lateinit var binding: FragmentMenuDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        val cartViewModel = CartViewModel(requireContext())

        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val menuItem = MenuDetailFragmentArgs.fromBundle(requireArguments()).menuItem

        binding.itemNameTextView.text = menuItem.name
        binding.itemPriceTextView.text = getString(R.string.price_format, menuItem.price)
        binding.itemDescriptionTextView.text = menuItem.description
        Picasso.get().load(menuItem.imageUrl).into(binding.itemImageView)



        val addToCartButton = view.findViewById<Button>(R.id.btnTambahCart)


        addToCartButton.setOnClickListener {

            val numberPesananEditText = view.findViewById<EditText>(R.id.inputNumberPesanan)
            val quantity = numberPesananEditText.text.toString().toInt()

            if (quantity > 0) {
                cartViewModel.addToCart(menuItem, quantity)
                Toast.makeText(requireContext(), "Makanan ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()


//                var namaItem=cartViewModel.getCartItems().value?.get(0)?.menuItem?.name;
//                var quantityy=cartViewModel.getCartItems().value?.get(0)?.quantity;
//                Toast.makeText(requireContext(), "Jumlah item di keranjang: $namaItem $quantityy", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "Kuantitas harus lebih besar dari 0", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }



}