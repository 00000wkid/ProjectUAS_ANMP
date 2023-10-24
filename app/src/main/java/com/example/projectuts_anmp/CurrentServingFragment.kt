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


class CurrentServingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_current_serving, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val tableNumber = arguments?.getInt("tableNumber")
//        Toast.makeText(requireContext(), tableNumber.toString(), Toast.LENGTH_SHORT).show()
        val sessionManager = SessionManager(requireContext())
        val tableNumber = sessionManager.getTableNumber()

        if (tableNumber != 0) {

            val servingText = "Currently Serving Table $tableNumber"

            val servingTextView = view.findViewById<TextView>(R.id.servingTextView)
            servingTextView.text = servingText
        }
        else{
            Toast.makeText(requireContext(), "Nomor meja tidak tersedia", Toast.LENGTH_SHORT).show()
        }
        val btnDoneServing = view.findViewById<Button>(R.id.btnDoneServing)

        btnDoneServing.setOnClickListener {
            val sessionManager = SessionManager(requireContext())
            sessionManager.clearTableNumber()
            val navController = findNavController()
            navController.popBackStack()
        }
    }

}