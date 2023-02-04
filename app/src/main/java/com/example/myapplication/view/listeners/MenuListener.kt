package com.example.myapplication.view.listeners

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.view.fragments.CartFragment
import com.example.myapplication.view.fragments.ProductsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuListener (private val activity: AppCompatActivity): BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuItemProductList -> {
                goToFragment(ProductsFragment())
                true
            }
            R.id.menuItemCart -> {
                goToFragment(CartFragment())
                true
            }
            else -> false
        }
    }

    private fun goToFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}