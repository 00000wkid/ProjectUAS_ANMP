package com.example.projectuts_anmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find your DrawerLayout, NavigationView, and NavController
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        drawerLayout.post { actionBarDrawerToggle.syncState() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Find your BottomNavigationView
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set up the NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up the click listeners for side menu items
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.navigation_menu -> {
                    navController.navigate(R.id.menuFragment)
                }
                R.id.navigation_cart -> {
                    navController.navigate(R.id.cartFragment)
                }
                R.id.navigation_orders -> {
                    navController.navigate(R.id.ordersFragment)
                }
                R.id.navigation_account -> {
                    navController.navigate(R.id.accountFragment)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Set up the click listeners for bottom navigation items
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.navigation_menu -> {
                    navController.navigate(R.id.menuFragment)
                }
                R.id.navigation_cart -> {
                    navController.navigate(R.id.cartFragment)
                }
R.id.navigation_orders -> {
                    navController.navigate(R.id.ordersFragment)
                }
                R.id.navigation_account -> {
                    navController.navigate(R.id.accountFragment)
                }

            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
