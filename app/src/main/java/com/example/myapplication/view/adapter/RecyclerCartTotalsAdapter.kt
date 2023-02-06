package com.example.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.models.CartItemTotals
import com.example.myapplication.viewmodel.CartViewModel
import javax.inject.Inject

class RecyclerCartTotalsAdapter @Inject constructor(private val context: Context?):
    RecyclerView.Adapter<RecyclerCartTotalsAdapter.CartTotalsHolder>() {

    private var cartListTotals: List<CartItemTotals> = emptyList()

    fun setCartListTotals(cartListTotals: List<CartItemTotals>) {
        this.cartListTotals = cartListTotals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartTotalsHolder {
        return CartTotalsHolder(LayoutInflater.from(context).inflate(R.layout.item_row_cart_totals, parent, false))
    }

    override fun onBindViewHolder(holder: CartTotalsHolder, position: Int) {
        val cartTotalItem = cartListTotals[position]
        with(holder) {
            tvCartTotalsDescription.text = cartTotalItem.description
            tvCartTotalsPrice.text = cartTotalItem.price.toString()
        }
    }

    override fun getItemCount() = cartListTotals.size

    inner class CartTotalsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvCartTotalsPrice: TextView = itemView.findViewById(R.id.tv_cart_totals_description)
        val tvCartTotalsDescription: TextView = itemView.findViewById(R.id.tv_cart_totals_price)
    }
}