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
import com.example.myapplication.model.models.ProductItem
import com.example.myapplication.viewmodel.CartViewModel

class RecyclerProductsAdapter(private val context: Context?):
    RecyclerView.Adapter<RecyclerProductsAdapter.ProductsHolder>() {

    private var productsList: List<ProductItem> = emptyList()

    fun setProductList(productsList: List<ProductItem>) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row_product_list, parent, false)
        return ProductsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val product = productsList[position]
        with(holder) {
            tvCode.text = product.code
            tvName.text = product.name
            tvPrice.text = product.price.toString()
            btnAddProductItem.setOnClickListener {
                CartViewModel.addCartProductItem(CartProductItem(1, productsList[position]))
            }
        }
    }

    override fun getItemCount() = productsList.size

    inner class ProductsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvCode: TextView = itemView.findViewById(R.id.tv_product_code)
        val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        val btnAddProductItem: Button = itemView.findViewById(R.id.btn_product_add_to_cart)
    }
}