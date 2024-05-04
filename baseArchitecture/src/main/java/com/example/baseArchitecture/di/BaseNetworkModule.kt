package com.example.baseArchitecture.di
import com.example.baseArchitecture.BuildConfig
import com.example.baseArchitecture.dataLayer.network.RequestInterceptor
import com.example.baseArchitecture.domain.IBaseUrlProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
open class BaseNetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, baseUrlProvider: IBaseUrlProvider): Retrofit {
        val baseUrl = baseUrlProvider.getBaseUrl()
        return Retrofit.Builder().baseUrl(if (!baseUrl.isNullOrEmpty()) baseUrl else "")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.apply {
            connectTimeout(5L, TimeUnit.MINUTES)
              if (BuildConfig.DEBUG) builder.addInterceptor(providerHttpsLoggingInterceptor())
            builder.addInterceptor(requestInterceptor)
        }
        return builder.build()
    }




    @Provides
    fun provideRequestInterceptor() : RequestInterceptor {
        return RequestInterceptor("")
    }

    fun getInterceptors(): ArrayList<Interceptor> {
        return arrayListOf()
    }

    @Singleton
    @Provides
    fun providerHttpsLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}