package challenges.cabify.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import challenges.cabify.myapplication.Constants.CURRENCY_SYMBOL
import challenges.cabify.myapplication.model.models.CartProductItem
import challenges.cabify.myapplication.viewmodel.CartViewModel
import com.cabify.myapplication.R
import javax.inject.Inject

/**
 * This class implements a RecyclerView.Adapter that is used to show the list of products in the cart.
 *
 * @param context The context to inflate the layout in.
 * @param cartViewModel The CartViewModel that holds the cart products list and manages it.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
class RecyclerCartProductListAdapter @Inject constructor(private val context: Context?, private val cartViewModel: CartViewModel):
    RecyclerView.Adapter<RecyclerCartProductListAdapter.CartProductHolder>() {

    private var cartProductsList: List<CartProductItem> = emptyList()

    /**
     * Sets the cart products list and updates the adapter.
     *
     * @param cartProductsList The list of [CartProductItem] to be displayed in the RecyclerView.
     */
    fun setCartProductsList(cartProductsList: List<CartProductItem>) {
        this.cartProductsList = cartProductsList
        notifyDataSetChanged()
    }

    /**
     * Creates a new ViewHolder to hold the UI components for each item in the RecyclerView.
     *
     * @param parent the parent ViewGroup that holds the created ViewHolder.
     * @param viewType the type of the ViewHolder.
     * @return a new instance of [CartProductHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductHolder {
        return CartProductHolder(LayoutInflater.from(context).inflate(R.layout.item_row_cart_product_list, parent, false))
    }

    /**
     * Binds the [CartProductItem] at the given [position] to the [holder].
     *
     * @param holder The [CartProductHolder] to bind the [CartProductItem].
     * @param position The position of the [CartProductItem] in the [cartProductsList].
     */
    override fun onBindViewHolder(holder: CartProductHolder, position: Int) {
        val cartProduct = cartProductsList[position]
        with(holder) {
            tvCartItemCode.text = cartProduct.productItem.code
            tvCartItemName.text = cartProduct.productItem.name
            tvCartItemPrice.text = CURRENCY_SYMBOL + cartProduct.productItem.price
            tvCartItemCount.text = cartProduct.itemCount.toString()
            btnCartItemPlus.setOnClickListener {
                cartViewModel.addCartProductItem(cartProduct)
            }
            btnCartItemMinus.setOnClickListener {
                cartViewModel.removeCartProductItem(cartProduct)
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = cartProductsList.size

    /**
     * Inner class that represents a view holder for the cart products list.
     *
     * @property btnCartItemPlus The plus button for increasing the quantity of the cart item.
     * @property btnCartItemMinus The minus button for decreasing the quantity of the cart item.
     * @property tvCartItemCount The text view for displaying the quantity of the cart item.
     * @property tvCartItemCode The text view for displaying the code of the cart item's product.
     * @property tvCartItemName The text view for displaying the name of the cart item's product.
     * @property tvCartItemPrice The text view for displaying the price of the cart item's product.
     *
     * @constructor Creates a view holder for the cart products list.
     * @param itemView The view that represents the cart product item.
     */
    inner class CartProductHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnCartItemPlus: Button = itemView.findViewById(R.id.btn_cart_plus)
        val btnCartItemMinus: Button = itemView.findViewById(R.id.btn_cart_minus)
        val tvCartItemCount: TextView = itemView.findViewById(R.id.tv_cart_item_count)
        val tvCartItemCode: TextView = itemView.findViewById(R.id.tv_cart_product_code)
        val tvCartItemName: TextView = itemView.findViewById(R.id.tv_cart_product_name)
        val tvCartItemPrice: TextView = itemView.findViewById(R.id.tv_cart_product_price)
    }
}