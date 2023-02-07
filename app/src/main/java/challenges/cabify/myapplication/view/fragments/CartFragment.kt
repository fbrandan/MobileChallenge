package challenges.cabify.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import challenges.cabify.myapplication.view.adapter.RecyclerCartProductListAdapter
import challenges.cabify.myapplication.view.adapter.RecyclerCartTotalsAdapter
import challenges.cabify.myapplication.viewmodel.CartViewModel
import com.cabify.myapplication.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A [Fragment] subclass that displays a list of products in a shopping cart using [RecyclerView].
 *
 * @property cartViewModel: [CartViewModel] The view model for this fragment.
 *
 * @constructor Creates a new instance of [CartFragment].
 * @param cartViewModel: [CartViewModel] The view model for this fragment.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
@AndroidEntryPoint
class CartFragment @Inject constructor(private val cartViewModel: CartViewModel) : Fragment() {
    private lateinit var binding: FragmentCartBinding

    private var recyclerCartProductList: RecyclerView? = null
    private lateinit var recyclerCartProductListAdapter: RecyclerCartProductListAdapter

    private var recyclerCartTotals: RecyclerView? = null
    private lateinit var recyclerCartTotalsAdapter: RecyclerCartTotalsAdapter

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
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    /**
    * Called when the fragment's view has been fully created.
    * @param view The View returned by [onCreateView].
    * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
    }

    /**
     * Initializes the view of this fragment.
     */
    private fun initViews() {
        recyclerCartProductList = binding.recyclerCartProducts
        recyclerCartProductList?.layoutManager = LinearLayoutManager(context)
        recyclerCartProductListAdapter = RecyclerCartProductListAdapter(context, cartViewModel)
        recyclerCartProductList?.adapter = recyclerCartProductListAdapter

        recyclerCartTotals = binding.recyclerCartTotals
        recyclerCartTotals?.layoutManager = LinearLayoutManager(context)
        recyclerCartTotalsAdapter = RecyclerCartTotalsAdapter(context)
        recyclerCartTotals?.adapter = recyclerCartTotalsAdapter
    }

    /**
     * Initializes the view models for this fragment.
     */
    private fun initViewModels() {
        cartViewModel.cartProductsLiveData.observe(viewLifecycleOwner) {
            recyclerCartProductListAdapter.setCartProductsList(it)
        }
        cartViewModel.cartTotalsLiveData.observe(viewLifecycleOwner) {
            recyclerCartTotalsAdapter.setCartListTotals(it)
        }
    }
}