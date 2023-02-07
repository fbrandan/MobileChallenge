package challenges.cabify.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import challenges.cabify.myapplication.model.models.CartProductItem
import challenges.cabify.myapplication.model.models.ProductItem
import challenges.cabify.myapplication.viewmodel.CartViewModel
import com.cabify.myapplication.R
import javax.inject.Inject

/**
 * RecyclerProductsAdapter is a RecyclerView.Adapter that is used to display a list of `ProductItem` objects.
 *
 * @param context The context from which the adapter was instantiated.
 * @param cartViewModel The ViewModel that is used to interact with the underlying cart data.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
class RecyclerProductsAdapter @Inject constructor(private val context: Context?, private val cartViewModel: CartViewModel):
    RecyclerView.Adapter<RecyclerProductsAdapter.ProductsHolder>() {

    private var productsList: List<ProductItem> = emptyList()

    /**
     * Sets the list of products that should be displayed by the adapter.
     *
     * @param productsList The list of `ProductItem` objects that should be displayed.
     */
    fun setProductList(productsList: List<ProductItem>) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    /**
     * Called when a new ViewHolder is needed for a new item in the list.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ProductsHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        return ProductsHolder(LayoutInflater.from(context).inflate(R.layout.item_row_product_list, parent, false))
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val product = productsList[position]
        with(holder) {
            tvCode.text = product.code
            tvName.text = product.name
            tvPrice.text = product.price.toString()
            btnAddProductItem.setOnClickListener {
                cartViewModel.addCartProductItem(CartProductItem(1, productsList[position]))
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = productsList.size

    /**
     * ProductsHolder is a RecyclerView.ViewHolder that is used to display a single item in the list of `ProductItem` objects.
     *
     * @param itemView The View that represents a single item in the list.
     */
    inner class ProductsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvCode: TextView = itemView.findViewById(R.id.tv_product_code)
        val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        val btnAddProductItem: Button = itemView.findViewById(R.id.btn_product_add_to_cart)
    }
}