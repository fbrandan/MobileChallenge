package com.example.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductListBinding
import com.example.myapplication.view.adapter.RecyclerProductsAdapter
import com.example.myapplication.viewmodel.ProductsViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: ProductsViewModel
    private lateinit var recyclerAdapter: RecyclerProductsAdapter
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initRecyclerView() {
        recycler = binding.root.findViewById(R.id.recycler_view_products)
        recycler?.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = RecyclerProductsAdapter(context)
        recycler?.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setProductList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG)
            }
        }

        viewModel.getProducts()
    }
}