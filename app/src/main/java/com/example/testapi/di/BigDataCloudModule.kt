package com.example.testapi.di

import com.example.testapi.data.api.BigDataCloudApi
import com.example.testapi.domain.repository.BigDataCloudRepository
import com.example.testapi.domain.usecase.GetPositionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dns
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BigDataCloudModule {

    @Provides
    fun provideGetPositionUseCase(
        repository: BigDataCloudRepository
    ) = GetPositionUseCase(repository)

    @Provides
    @Singleton
    @Named("bigdatacloud")
    fun provideRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()

            // Только IPv4
            .dns(object : Dns {
                override fun lookup(hostname: String): List<InetAddress> {
                    return Dns.SYSTEM.lookup(hostname)
                        .filterIsInstance<Inet4Address>()
                }
            })

            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.bigdatacloud.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        @Named("bigdatacloud") retrofit: Retrofit
    ): BigDataCloudApi {
        return retrofit.create(BigDataCloudApi::class.java)
    }
}