package com.example.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row_product_list, parent, false)
        return ProductsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val product = productsList[position]
        holder.tvCode.text = product.code
        holder.tvName.text = product.name
        holder.tvPrice.text = product.price.toString()
        holder.btnAddProductItem.setOnClickListener {
            CartViewModel.addCartProductItem(CartProductItem(1, productsList[position]))
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    inner class ProductsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCode: TextView
        var tvName: TextView
        var tvPrice: TextView
        var btnAddProductItem: Button

        init {
            tvCode = itemView.findViewById(R.id.tv_product_code)
            tvName = itemView.findViewById(R.id.tv_product_name)
            tvPrice = itemView.findViewById(R.id.tv_product_price)
            btnAddProductItem = itemView.findViewById(R.id.btn_product_add_to_cart)
        }
    }
}