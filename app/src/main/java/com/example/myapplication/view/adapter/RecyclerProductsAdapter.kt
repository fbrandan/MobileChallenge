package com.example.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.models.Product

class RecyclerProductsAdapter(var context: Context?):
    RecyclerView.Adapter<RecyclerProductsAdapter.ProductsHolder>() {

    private var productsList: List<Product>? = null

    inner class ProductsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCode: TextView
        var tvName: TextView
        var tvPrice: TextView

        init {
            tvCode = itemView.findViewById(R.id.tvItemRowCode)
            tvName = itemView.findViewById(R.id.tvItemRowName)
            tvPrice = itemView.findViewById(R.id.tvItemRowPrice)
        }

    }

    fun setProductList(productsList: List<Product>) {
        this.productsList = productsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ProductsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val product = productsList?.get(position)
        holder.tvCode.text = product?.code
        holder.tvName.text = product?.name
        holder.tvPrice.text = product?.price.toString()
    }

    override fun getItemCount(): Int {
        return productsList?.size ?: 0
    }
}