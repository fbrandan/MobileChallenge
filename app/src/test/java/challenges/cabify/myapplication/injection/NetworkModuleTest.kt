package challenges.cabify.myapplication.injection

import org.junit.Test
import retrofit2.Retrofit

class NetworkModuleTest {

    @Test
    fun providesRetrofit() {
        assert(NetworkModule().providesRetrofit().javaClass == Retrofit::class.java)
    }

    @Test
    fun providesAPIService() {
        assert(NetworkModule().providesAPIService(NetworkModule().providesRetrofit()) != null)
    }
}