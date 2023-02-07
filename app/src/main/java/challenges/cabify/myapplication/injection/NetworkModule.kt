package challenges.cabify.myapplication.injection

import challenges.cabify.myapplication.Constants.BASE_URL
import challenges.cabify.myapplication.model.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * NetworkModule is a module class responsible for providing instances of Retrofit and APIService.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    /**
     * Provides a singleton instance of Retrofit.
     *
     * @return a singleton instance of Retrofit.
     */
    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides a singleton instance of APIService.
     *
     * @param retrofit an instance of Retrofit.
     * @return a singleton instance of APIService.
     */
    @Singleton
    @Provides
    fun providesAPIService(retrofit: Retrofit) : APIService {
        return retrofit.create(APIService::class.java)
    }
}