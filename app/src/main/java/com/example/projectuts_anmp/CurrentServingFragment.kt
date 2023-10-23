package com.example.projectuts_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrentServingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrentServingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_serving, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val tableNumber = arguments?.getInt("tableNumber")
//        Toast.makeText(requireContext(), tableNumber.toString(), Toast.LENGTH_SHORT).show()
        val sessionManager = SessionManager(requireContext())
        val tableNumber = sessionManager.getTableNumber()

        if (tableNumber != 0) {
            // Lakukan sesuatu dengan nomor meja, misalnya menampilkan "Currently Serving Table X"
            val servingText = "Currently Serving Table $tableNumber"
            // Contoh menampilkan pesan pada TextView
            val servingTextView = view.findViewById<TextView>(R.id.servingTextView)
            servingTextView.text = servingText
        }
        else{
            Toast.makeText(requireContext(), "Nomor meja tidak tersedia", Toast.LENGTH_SHORT).show()
        }
        val btnDoneServing = view.findViewById<Button>(R.id.btnDoneServing)

        btnDoneServing.setOnClickListener {
            val sessionManager = SessionManager(requireContext())

            // Hapus nomor meja dari sesi
            sessionManager.clearTableNumber()

            // Kembali ke HomeFragment
            val navController = findNavController()
            navController.popBackStack() // Ini akan menghapus CurrentServingFragment dari tumpukan kembali
        }
    }

}