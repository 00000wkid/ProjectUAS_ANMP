package com.example.projectuts_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class OrdersFragment : Fragment() {
    private val sessionManager: SessionManager by lazy { SessionManager(requireContext()) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: com.example.projectuts_anmp.CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

}