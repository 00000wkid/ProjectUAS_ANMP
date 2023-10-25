package com.example.projectuts_anmp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class AccountFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get input
        val view = inflater.inflate(R.layout.fragment_accounts, container, false)
        val sessionManager = SessionManager(requireContext())
        val changePassword= view.findViewById<TextView>(R.id.txtInputPassword)
        val repeatPassword= view.findViewById<TextView>(R.id.txtInputRepeat)
        val btnChange= view.findViewById<Button>(R.id.btnChange)
        val btnLogout= view.findViewById<Button>(R.id.btnLogout)

        btnChange.setOnClickListener {
//            Toast.makeText(requireContext(), "Password Berhasil Diubah", Toast.LENGTH_SHORT).show()
            if (changePassword.text.toString() == repeatPassword.text.toString()) {
                sessionManager.saveToken("nope")
                sessionManager.savePassword(changePassword.text.toString())
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)

            } else {
                changePassword.error = "Password tidak sama"
                repeatPassword.error = "Password tidak sama"
            }
        }

        btnLogout.setOnClickListener {
            sessionManager.saveToken("nope")
            Toast.makeText(requireContext(), "Logout Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

        }




        return view
    }




}