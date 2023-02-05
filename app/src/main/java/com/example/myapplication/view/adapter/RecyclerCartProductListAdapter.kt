package com.example.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.models.CartProductItem
import com.example.myapplication.viewmodel.CartViewModel

class RecyclerCartProductListAdapter(private val context: Context?):
    RecyclerView.Adapter<RecyclerCartProductListAdapter.CartProductHolder>() {

    private var cartProductsList: List<CartProductItem> = emptyList()

    fun setCartProductsList(cartProductsList: List<CartProductItem>) {
        this.cartProductsList = cartProductsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row_cart_product_list, parent, false)
        return CartProductHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartProductHolder, position: Int) {
        val cartProduct = cartProductsList[position]
        with(holder) {
            tvCartItemCode.text = cartProduct.productItem.code
            tvCartItemName.text = cartProduct.productItem.name
            tvCartItemPrice.text = cartProduct.productItem.price.toString()
            tvCartItemCount.text = cartProduct.itemCount.toString()
            btnCartItemPlus.setOnClickListener {
                CartViewModel.addCartProductItem(cartProduct)
            }
            btnCartItemMinus.setOnClickListener {
                CartViewModel.removeCartProductItem(cartProduct)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartProductsList.size
    }

    inner class CartProductHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnCartItemPlus: Button = itemView.findViewById(R.id.btn_cart_plus)
        val btnCartItemMinus: Button = itemView.findViewById(R.id.btn_cart_minus)
        val tvCartItemCount: TextView = itemView.findViewById(R.id.tv_cart_item_count)
        val tvCartItemCode: TextView = itemView.findViewById(R.id.tv_cart_product_code)
        val tvCartItemName: TextView = itemView.findViewById(R.id.tv_cart_product_name)
        val tvCartItemPrice: TextView = itemView.findViewById(R.id.tv_cart_product_price)
    }
}