package com.example.ygodeckbuilder.di

import com.example.ygodeckbuilder.rest.ApiHelper
import com.example.ygodeckbuilder.rest.RequestInterceptor
import com.example.ygodeckbuilder.rest.ResponseInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesRequestInterceptor(): RequestInterceptor =
        RequestInterceptor()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun providesYuGiOhApi(retrofit: Retrofit): ApiHelper =
        retrofit.create(ApiHelper::class.java)

}