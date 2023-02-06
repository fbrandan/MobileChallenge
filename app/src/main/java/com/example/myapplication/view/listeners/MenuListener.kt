package com.example.myapplication.view.listeners

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.view.fragments.CartFragment
import com.example.myapplication.view.fragments.ProductListFragment
import com.example.myapplication.viewmodel.CartViewModel
import com.example.myapplication.viewmodel.ProductsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MenuListener @Inject constructor(private val activity: AppCompatActivity, private val productsViewModel: ProductsViewModel, private val cartViewModel: CartViewModel): BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuItemProductList -> {
                goToFragment(ProductListFragment(productsViewModel,cartViewModel))
                true
            }
            R.id.menuItemCart -> {
                goToFragment(CartFragment(cartViewModel))
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