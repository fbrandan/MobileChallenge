package challenges.cabify.myapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import challenges.cabify.myapplication.view.fragments.ProductListFragment
import challenges.cabify.myapplication.view.listeners.MenuListener
import challenges.cabify.myapplication.viewmodel.CartViewModel
import challenges.cabify.myapplication.viewmodel.ProductsViewModel
import com.cabify.myapplication.R
import com.cabify.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
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