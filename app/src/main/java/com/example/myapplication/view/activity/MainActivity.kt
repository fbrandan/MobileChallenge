package com.example.myapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.fragments.ProductListFragment
import com.example.myapplication.view.listeners.MenuListener
import com.example.myapplication.viewmodel.CartViewModel
import com.example.myapplication.viewmodel.ProductsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navigation: BottomNavigationView by lazy { binding.navigationBarMain }

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeViewModels()
        initializeMenu()
        initializeFirstFragment()
    }

    private fun initializeViewModels() {
        productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
    }

    private fun initializeMenu() {
        navigation.setOnItemSelectedListener(MenuListener(this, productsViewModel, cartViewModel))
    }

    private fun initializeFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ProductListFragment(productsViewModel, cartViewModel))
            .commit()
    }
}