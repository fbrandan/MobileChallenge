package com.example.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentCartBinding
import com.example.myapplication.model.models.CartItemTotals
import com.example.myapplication.view.adapter.RecyclerCartProductListAdapter
import com.example.myapplication.view.adapter.RecyclerCartTotalsAdapter
import com.example.myapplication.viewmodel.CartViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CartFragment @Inject constructor(private val cartViewModel: CartViewModel) : Fragment() {
    private lateinit var binding: FragmentCartBinding

    private var recyclerCartProductList: RecyclerView? = null
    private lateinit var recyclerCartProductListAdapter: RecyclerCartProductListAdapter

    private var recyclerCartTotals: RecyclerView? = null
    private lateinit var recyclerCartTotalsAdapter: RecyclerCartTotalsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
    }

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

    private fun initViewModels() {
        cartViewModel.cartProductListLiveData.observe(viewLifecycleOwner) {
            recyclerCartProductListAdapter.setCartProductsList(it)
        }
        cartViewModel.cartItemTotalsListLiveData.observe(viewLifecycleOwner) {
            recyclerCartTotalsAdapter.setCartListTotals(it)
        }
    }
}