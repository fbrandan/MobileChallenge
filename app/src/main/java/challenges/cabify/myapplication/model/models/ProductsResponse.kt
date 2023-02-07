package challenges.cabify.myapplication.model.models

import challenges.cabify.myapplication.Constants.KEY_PRODUCT_RESPONSE
import com.google.gson.annotations.SerializedName
/**
 * Class to store the response from the API that contains a list of product items.
 * @property productsList The list of product items in the response.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
data class ProductsResponse(
    @SerializedName(KEY_PRODUCT_RESPONSE) var productsList: List<ProductItem>)
