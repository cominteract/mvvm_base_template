package com.ainsigne.data.di


import com.ainsigne.common.BASE_URL
import com.ainsigne.data.remote.api.FuturamaService
import com.ainsigne.data.remote.api.SwitchGameService
import com.ainsigne.data.utils.json
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val REQUEST_TIMEOUT = 10
    private var okHttpClient: OkHttpClient? = null

    @Provides
    @Singleton
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /*
    * The method returns the Okhttp object
    * */
    @Provides
    @Singleton
    internal fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor{chain ->
                val request = chain.request().newBuilder().addHeader(
                    "Connection",
                    "close"
                ).build()
                chain.proceed(request)
            }
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()

        return (okHttpClient)!!
    }

    /*
    * The method returns the Retrofit object
    * */
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(json())
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideSwitchGameService(retrofit: Retrofit): SwitchGameService {
        return retrofit.create(SwitchGameService::class.java)
    }

    @Provides
    @Singleton
    fun provideFuturamaService(retrofit: Retrofit): FuturamaService {
        return retrofit.create(FuturamaService::class.java)
    }

}