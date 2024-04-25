package com.example.imageloadingandinfinitescrolling.di


import com.example.baseArchitecture.di.BaseNetworkModule
import com.example.baseArchitecture.domain.IBaseUrlProvider
import com.example.imageloadingandinfinitescrolling.BuildConfig
import com.example.imageloadingandinfinitescrolling.domain.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule : BaseNetworkModule() {

 @Provides
 @Singleton
 fun baseUrlProviderMethod ( baseUrlProvider : IBaseUrlProvider)  : IBaseUrlProvider = BaseURLProvider()

     @Provides
    @Singleton
    fun provideApiService(retrofit : Retrofit ) : APIService = retrofit.create(APIService::class.java)
}
class BaseURLProvider() : IBaseUrlProvider{
    override fun getBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

}
