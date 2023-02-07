package challenges.cabify.myapplication.repository

import challenges.cabify.myapplication.model.models.ProductsResponse
import challenges.cabify.myapplication.model.network.APIService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ProductRepositoryTest {

    @RelaxedMockK
    private lateinit var apiService: APIService

    lateinit var productRepository: ProductRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        productRepository = ProductRepository(apiService)
    }

    @Test
    fun `when getProducts is called in the productRepository should call the getProducts on the apiService`() = runBlocking {
        val url = "palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json"
        //Given
        coEvery { apiService.getProducts(url) } returns Response.success(
            ProductsResponse(
            emptyList()
        )
        )

        //When
        productRepository.getProducts()

        //Then
        coVerify { apiService.getProducts(url) }
    }
}