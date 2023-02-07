package challenges.cabify.myapplication.view.listeners

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import challenges.cabify.myapplication.view.fragments.CartFragment
import challenges.cabify.myapplication.view.fragments.ProductListFragment
import challenges.cabify.myapplication.viewmodel.CartViewModel
import challenges.cabify.myapplication.viewmodel.ProductsViewModel
import com.cabify.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

/**
 *  MenuListener is a class that implements the [BottomNavigationView.OnNavigationItemSelectedListener] interface.
 *  This class is used to switch between fragments when a bottom navigation item is selected.
 *
 *  @param activity is the [AppCompatActivity] instance in which the fragments are displayed.
 *  @param productsViewModel is the [ProductsViewModel] instance that holds the data for the product list fragment.
 *  @param cartViewModel is the [CartViewModel] instance that holds the data for the cart fragment.
 *
 *  @constructor Creates an instance of the class with specified activity, productsViewModel, and cartViewModel.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
class MenuListener @Inject constructor(private val activity: AppCompatActivity, private val productsViewModel: ProductsViewModel, private val cartViewModel: CartViewModel): BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * Override method for [BottomNavigationView.OnNavigationItemSelectedListener.onNavigationItemSelected].
     * This method is called when a bottom navigation item is selected.
     *
     * @param item is the selected [MenuItem].
     * @return Returns true if the selection was handled successfully, false otherwise.
     */
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
    /**
     * Private helper method to navigate to a specified [Fragment].
     *
     * @param fragment is the [Fragment] instance to navigate to.
     */
    private fun goToFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}