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

/**
 * The MainActivity is the main activity class for the application,
 * which handles the main UI and the bottom navigation bar.
 *
 * @property binding ActivityMainBinding an instance of the main activity's data binding class.
 * @property navigation BottomNavigationView an instance of the main activity's bottom navigation bar.
 * @property productsViewModel ProductsViewModel an instance of the products view model.
 * @property cartViewModel CartViewModel an instance of the cart view model.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navigation: BottomNavigationView by lazy { binding.navigationBarMain }

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var cartViewModel: CartViewModel

    /**
     * Initializes the activity, sets up the data binding,
     * initializes the ViewModels, sets up the bottom navigation bar,
     * and shows the first fragment.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeViewModels()
        initializeMenu()
        initializeFirstFragment()
    }

    /**
     * Initializes the products and cart ViewModels.
     */
    private fun initializeViewModels() {
        productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
    }

    /**
     * Initializes the bottom navigation bar with a [MenuListener].
     */
    private fun initializeMenu() {
        navigation.setOnItemSelectedListener(MenuListener(this, productsViewModel, cartViewModel))
    }

    /**
     * Shows the first fragment for the main activity.
     */
    private fun initializeFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ProductListFragment(productsViewModel, cartViewModel))
            .commit()
    }
}