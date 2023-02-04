package com.example.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.models.CartItemTotals

class RecyclerCartTotalsAdapter(private val context: Context?):
    RecyclerView.Adapter<RecyclerCartTotalsAdapter.CartTotalsHolder>() {

    private var cartListTotals: List<CartItemTotals> = emptyList()

    fun setCartListTotals(cartListTotals: List<CartItemTotals>) {
        this.cartListTotals = cartListTotals
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartTotalsHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row_cart_totals, parent, false)
        return CartTotalsHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartTotalsHolder, position: Int) {
        var cartTotalItem = cartListTotals[position]
        holder.tvCartTotalsDescription.text = cartTotalItem.description
        holder.tvCartTotalsPrice.text = cartTotalItem.price.toString()
    }

    override fun getItemCount(): Int {
        return cartListTotals.size
    }

    inner class CartTotalsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCartTotalsDescription: TextView
        var tvCartTotalsPrice: TextView

        init {
            tvCartTotalsDescription = itemView.findViewById(R.id.tv_cart_totals_description)
            tvCartTotalsPrice = itemView.findViewById(R.id.tv_cart_totals_price)
        }

    }
}