package challenges.cabify.myapplication.model.models

import challenges.cabify.myapplication.Constants.KEY_PRODUCT_ITEM_CODE
import challenges.cabify.myapplication.Constants.KEY_PRODUCT_ITEM_NAME
import challenges.cabify.myapplication.Constants.KEY_PRODUCT_ITEM_PRICE
import com.google.gson.annotations.SerializedName

/**
 * Class to store information about a product item including the code, name, and price.
 * @property code The code of the product item.
 * @property name The name of the product item.
 * @property price The price of the product item.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
data class ProductItem(
    @SerializedName(KEY_PRODUCT_ITEM_CODE) val code: String,
    @SerializedName(KEY_PRODUCT_ITEM_NAME) val name: String,
    @SerializedName(KEY_PRODUCT_ITEM_PRICE) val price: Double)
