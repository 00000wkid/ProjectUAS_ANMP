package com.example.projectuts_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.NavHostFragment

class HomeFragment : Fragment() {
    // ...

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val submitButton = view.findViewById<Button>(R.id.btnSubmitTableNumber)

        submitButton.setOnClickListener {

            val tableNumberEditText = view.findViewById<EditText>(R.id.tableNumberEditText)
            val tableNumberText = tableNumberEditText.text.toString()

            try {

                val tableNumber = tableNumberText.toInt()


                if (tableNumber > 0) {

                    val action = HomeFragmentDirections.actionHomeFragmentToCurrentServingFragment()
                    findNavController().navigate(action)
                } else {

                    Toast.makeText(requireContext(), "Nomor meja harus lebih besar dari 0", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {

                Toast.makeText(requireContext(), "Nomor meja harus numerik", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }
}
