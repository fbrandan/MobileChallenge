package com.example.myapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.fragments.ProductListFragment
import com.example.myapplication.view.listeners.MenuListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navigation: BottomNavigationView by lazy { binding.navigationBarMain }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeMenu()
        initializeFirstFragment()
    }

    private fun initializeMenu() {
        navigation.setOnItemSelectedListener(MenuListener(this))
    }

    private fun initializeFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ProductListFragment())
            .commit()
    }
}