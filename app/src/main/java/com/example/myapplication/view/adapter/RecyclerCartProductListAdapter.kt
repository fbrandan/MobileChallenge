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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row_cart_product_list, parent, false)
        return CartProductHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartProductHolder, position: Int) {
        val cartProduct = cartProductsList[position]
        holder.tvCartItemCode.text = cartProduct.productItem.code
        holder.tvCartItemName.text = cartProduct.productItem.name
        holder.tvCartItemPrice.text = cartProduct.productItem.price.toString()
        holder.tvCartItemCount.text = cartProduct.itemCount.toString()
        holder.btnCartItemPlus.setOnClickListener {
            CartViewModel.addCartProductItem(cartProduct)
        }
        holder.btnCartItemMinus.setOnClickListener {
            CartViewModel.removeCartProductItem(cartProduct)
        }
    }

    override fun getItemCount(): Int {
        return cartProductsList.size
    }

    inner class CartProductHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var btnCartItemPlus: Button
        var btnCartItemMinus: Button
        var tvCartItemCount: TextView
        var tvCartItemCode: TextView
        var tvCartItemName: TextView
        var tvCartItemPrice: TextView

        init {
            btnCartItemPlus = itemView.findViewById(R.id.btn_cart_plus)
            btnCartItemMinus = itemView.findViewById(R.id.btn_cart_minus)
            tvCartItemCount = itemView.findViewById(R.id.tv_cart_item_count)
            tvCartItemCode = itemView.findViewById(R.id.tv_cart_product_code)
            tvCartItemName = itemView.findViewById(R.id.tv_cart_product_name)
            tvCartItemPrice = itemView.findViewById(R.id.tv_cart_product_price)
        }
    }
}