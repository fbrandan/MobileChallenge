package challenges.cabify.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import challenges.cabify.myapplication.Constants.DEFAULT_TIMEOUT_MILISECONDS
import challenges.cabify.myapplication.Constants.PRODUCTS_PATH
import challenges.cabify.myapplication.model.models.ProductItem
import challenges.cabify.myapplication.model.network.APIService
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.time.Duration

/**
 * ProductRepository is a class that is responsible for retrieving product data from a server.
 *
 * It makes use of the APIService class to fetch the product data in a suspended function, and
 * posts the result to a MutableLiveData. The class makes use of CoroutineScope and Dispatchers.IO
 * to fetch the data asynchronously on a background thread.
 *
 * @param apiService: The APIService instance to use for fetching the product data.
 *
 * This class provides one method:
 * - getProducts.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
class ProductRepository @Inject constructor(private val apiService: APIService) {
    private val _productsList = MutableLiveData<List<ProductItem>>()
    val productList: LiveData<List<ProductItem>>
    get() = _productsList

    /**
     * Retrieves the product data from the server using the APIService instance, and posts the
     * result to the MutableLiveData.
     * This has a [DEFAULT_TIMEOUT_MILISECONDS] because the task could not finish if the server
     * response takes too long.
     */
    fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            withTimeout(DEFAULT_TIMEOUT_MILISECONDS) {
                val result =
                    apiService.getProducts(PRODUCTS_PATH)
                if (result.isSuccessful && result.body() != null) {
                    _productsList.postValue(result.body()!!.productsList)
                } else {
                    _productsList.postValue(emptyList())
                }
            }
        }
    }
}