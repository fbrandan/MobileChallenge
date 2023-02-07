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