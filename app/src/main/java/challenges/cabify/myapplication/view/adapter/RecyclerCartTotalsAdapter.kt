package challenges.cabify.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import challenges.cabify.myapplication.model.models.CartItemTotals
import com.cabify.myapplication.R
import javax.inject.Inject

/**
 * An adapter for displaying a list of [CartItemTotals].
 *
 * @param context The context to be used by the adapter.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
class RecyclerCartTotalsAdapter @Inject constructor(private val context: Context?):
    RecyclerView.Adapter<RecyclerCartTotalsAdapter.CartTotalsHolder>() {

    private var cartListTotals: List<CartItemTotals> = emptyList()

    /**
     * Sets the list of [CartItemTotals] to be displayed by the adapter.
     *
     * @param cartListTotals The list of [CartItemTotals] to be displayed.
     */
    fun setCartListTotals(cartListTotals: List<CartItemTotals>) {
        this.cartListTotals = cartListTotals
        notifyDataSetChanged()
    }

    /**
     * Creates a new [CartTotalsHolder] to display the data at the given position.
     *
     * @param parent The parent [ViewGroup] the new [CartTotalsHolder] will be attached to.
     * @param viewType The view type of the new [CartTotalsHolder].
     * @return A new [CartTotalsHolder] to display the data at the given position.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartTotalsHolder {
        return CartTotalsHolder(LayoutInflater.from(context).inflate(R.layout.item_row_cart_totals, parent, false))
    }

    /**
     * Binds the [CartItemTotals] at the given position to the given [CartTotalsHolder].
     *
     * @param holder The [CartTotalsHolder] to be used to display the data.
     * @param position The position of the [CartItemTotals] to be displayed.
     */
    override fun onBindViewHolder(holder: CartTotalsHolder, position: Int) {
        val cartTotalItem = cartListTotals[position]
        with(holder) {
            tvCartTotalsDescription.text = cartTotalItem.description
            tvCartTotalsPrice.text = cartTotalItem.price.toString()
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = cartListTotals.size

    /**
     * A holder for displaying the details of a [CartItemTotals].
     *
     * @param itemView The view for the [CartTotalsHolder].
     */
    inner class CartTotalsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvCartTotalsPrice: TextView = itemView.findViewById(R.id.tv_cart_totals_description)
        val tvCartTotalsDescription: TextView = itemView.findViewById(R.id.tv_cart_totals_price)
    }
}