package com.example.myapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.fragments.ProductListFragment
import com.example.myapplication.view.listeners.MenuController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navigation: BottomNavigationView
    private val navigationListener = MenuController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeMenu()
        initializeFirstFragment()
    }

    private fun initializeMenu() {
        navigation = binding.navigationBarMain
        navigation.setOnItemSelectedListener(navigationListener)
    }

    private fun initializeFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ProductListFragment())
            .commit()
    }
}