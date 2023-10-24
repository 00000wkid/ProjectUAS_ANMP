package com.example.projectuts_anmp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuts_anmp.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle any arguments here if needed
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inisialisasi ViewModel
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        // Inisialisasi RecyclerView
        val cartRecyclerView = binding.cartRecyclerView

        cartAdapter = CartAdapter(requireContext(), cartViewModel.getCartItems().value.orEmpty())
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.adapter = cartAdapter

        // Amati LiveData dari ViewModel
        cartViewModel.getCartItems().observe(viewLifecycleOwner) { items ->
            // Perubahan pada LiveData akan secara otomatis memperbarui RecyclerView
        }

        cartAdapter.setOnItemClickListener { position ->
            showDeleteConfirmationDialog(position)
        }

        return view
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Hapus Item")
        builder.setMessage("Apakah Anda yakin ingin menghapus item dari keranjang?")
        builder.setPositiveButton("Ya") { _, _ ->
            // Hapus item dari keranjang jika pengguna menekan "Ya"
            cartViewModel.removeItem(position)
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            // Batalkan penghapusan jika pengguna menekan "Tidak"
            dialog.dismiss()
        }
        builder.create().show()
    }
}
