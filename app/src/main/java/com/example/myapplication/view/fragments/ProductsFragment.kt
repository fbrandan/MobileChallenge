package com.example.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentProductListBinding
import com.example.myapplication.model.models.ProductItem
import com.example.myapplication.view.adapter.RecyclerProductsAdapter
import com.example.myapplication.viewmodel.CartViewModel
import com.example.myapplication.viewmodel.ProductsViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var productViewModel: ProductsViewModel
    private lateinit var recyclerProductsAdapter: RecyclerProductsAdapter
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        recycler = binding.recyclerViewProducts
        recycler?.layoutManager = LinearLayoutManager(context)
        recyclerProductsAdapter = RecyclerProductsAdapter(context)
        recycler?.adapter = recyclerProductsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
    }

    private fun initViewModels() {
        productViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        productViewModel.getLiveData().observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerProductsAdapter.setProductList(it)
                recyclerProductsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG)
            }
        }

        productViewModel.getProducts()
    }
}