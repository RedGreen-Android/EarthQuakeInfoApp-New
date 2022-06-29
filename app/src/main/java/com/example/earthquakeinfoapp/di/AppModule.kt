package com.example.earthquakeinfoapp.di

import com.example.earthquakeinfoapp.data.network.EarthquakeAPI
import com.example.earthquakeinfoapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getRetrofitApi(retrofit: Retrofit) : EarthquakeAPI{
        return retrofit.create(EarthquakeAPI::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}