package challenges.cabify.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import challenges.cabify.myapplication.Constants.DEFAULT_NETWORK_ERROR_MESSAGE
import challenges.cabify.myapplication.view.adapter.RecyclerProductsAdapter
import challenges.cabify.myapplication.viewmodel.CartViewModel
import challenges.cabify.myapplication.viewmodel.ProductsViewModel
import com.cabify.myapplication.R
import com.cabify.myapplication.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
/**
 * A [Fragment] subclass that displays a list of products in a shopping list [RecyclerView].
 *
 * @constructor Creates a new instance of [ProductListFragment].
 * @property productViewModel: [ProductsViewModel] The view model for this fragment.
 * @param cartViewModel: [CartViewModel] The view model for this fragment.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
class ProductListFragment @Inject constructor(private var productViewModel: ProductsViewModel, private var cartViewModel: CartViewModel) : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var recyclerAdapter: RecyclerProductsAdapter
    private var recycler: RecyclerView? = null

    /**
     * Inflates the layout for this fragment, sets up the views, and initializes the view models.
     *
     * @param inflater [LayoutInflater] The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container [ViewGroup] If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState [Bundle] If non-null, this fragment is being re-constructed from a previous saved state as given here.
     *
     * @return [View] The view for this fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        initViews()
        initViewModel()
        return binding.root
    }

    /**
     * Initializes the view of this fragment.
     */
    private fun initViews() {
        recycler = binding.root.findViewById(R.id.recycler_view_products)
        recycler?.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = RecyclerProductsAdapter(context, cartViewModel)
        recycler?.adapter = recyclerAdapter
    }

    /**
     * Initializes the view models for this fragment.
     */
    private fun initViewModel() {
        productViewModel.productsLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setProductList(it)
            } else {
                Toast.makeText(context, DEFAULT_NETWORK_ERROR_MESSAGE, Toast.LENGTH_LONG)
            }
        }
    }
}