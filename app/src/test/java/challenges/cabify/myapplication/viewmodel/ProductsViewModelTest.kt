package challenges.cabify.myapplication.viewmodel

import challenges.cabify.myapplication.repository.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductsViewModelTest {

    @RelaxedMockK
    private lateinit var productRepository: ProductRepository

    lateinit var productsViewModel: ProductsViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        productsViewModel = ProductsViewModel(productRepository)
    }

    @Test
    fun `when you get the productsLiveData is not a null object`() = runBlocking {

        //When
        val liveData = productsViewModel.productsLiveData

        //Then
        assert(liveData != null)
    }

    @Test
    fun `when you create a new instance of ProductViewModel the getProducts() from the repository is called`() = runBlocking {

        //When
        productsViewModel = ProductsViewModel(productRepository)

        //Then
        coVerify { productRepository.getProducts() }
    }
}