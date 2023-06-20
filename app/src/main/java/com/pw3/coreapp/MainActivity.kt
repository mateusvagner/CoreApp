package com.pw3.coreapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.ActivityMainBinding
import com.pw3.coreapp.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView: BottomNavigationView = binding.bottomNavView
        val drawerNavView: NavigationView = binding.drawerNavView
        val drawerLayout: DrawerLayout = binding.drawerLayout

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.main, R.id.students, R.id.calendar, R.id.financial, R.id.more
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
        drawerNavView.setupWithNavController(navController)

        drawerNavView.menu.findItem(R.id.logout).setOnMenuItemClickListener {
            viewModel.logoutUser()
            val intent = intent
            finish()
            startActivity(intent)
            return@setOnMenuItemClickListener true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }

    fun setDrawerMenuVisibility(isVisible: Boolean) {
        val lockMode =
            if (isVisible) DrawerLayout.LOCK_MODE_UNLOCKED
            else DrawerLayout.LOCK_MODE_LOCKED_CLOSED

        binding.drawerLayout.setDrawerLockMode(lockMode, binding.drawerNavView)
        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
    }

    fun setBottomNavViewVisibility(isVisible: Boolean) {
        binding.bottomNavView.isVisible = isVisible
    }
}