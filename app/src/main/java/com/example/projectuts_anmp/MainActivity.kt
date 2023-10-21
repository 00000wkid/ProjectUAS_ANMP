package com.example.projectuts_anmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_menu -> {
                    navController.navigate(R.id.menuFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_cart -> {
                    navController.navigate(R.id.cartFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_orders -> {
                    navController.navigate(R.id.ordersFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_account -> {
                    navController.navigate(R.id.accountFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    // Hapus metode replaceFragment karena Anda tidak akan menggunakannya lagi.
}
