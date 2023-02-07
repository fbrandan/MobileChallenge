package challenges.cabify.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import challenges.cabify.myapplication.view.adapter.RecyclerProductsAdapter
import challenges.cabify.myapplication.viewmodel.CartViewModel
import challenges.cabify.myapplication.viewmodel.ProductsViewModel
import com.cabify.myapplication.R
import com.cabify.myapplication.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ProductListFragment @Inject constructor(private var viewModel: ProductsViewModel, private var cartViewModel: CartViewModel) : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var recyclerAdapter: RecyclerProductsAdapter
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        initRecyclerView()
        initViewModel()
        return binding.root
    }

    private fun initRecyclerView() {
        recycler = binding.root.findViewById(R.id.recycler_view_products)
        recycler?.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = RecyclerProductsAdapter(context, cartViewModel)
        recycler?.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setProductList(it)
            } else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG)
            }
        }
    }
}