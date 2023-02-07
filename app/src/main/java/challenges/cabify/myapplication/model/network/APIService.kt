package challenges.cabify.myapplication.model.network

import challenges.cabify.myapplication.model.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Interface for the API Service to make a GET request to retrieve the list of product items.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
interface APIService {

    /**
     * Suspend function to make a GET request to retrieve the list of product items from the specified URL.
     * @param pathUrl The URL for the API request.
     * @return A response from the API that contains the list of product items.
     */
    @GET
    suspend fun getProducts(@Url pathUrl: String):Response<ProductsResponse>
}