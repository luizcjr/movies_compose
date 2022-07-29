package com.example.movies.data.di

import com.example.movies.data.network.interceptor.MoviesInterceptor
import com.example.movies.data.network.service.MoviesService
import com.example.movies.data.repository.MoviesRepository
import com.example.movies.data.repository.MoviesRepositoryImpl
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModules {
    private const val connectionTimeoutSeconds = 20 * 1000L
    private const val url = "https://api.themoviedb.org/3/"
    private const val API_KEY = "c3dff9afe1b1e8a1ecf6d6de20924f5c"

    fun load() {
        loadKoinModules(repositoryModule() + networkModule())
    }

    private fun repositoryModule(): Module {
        return module {
            single<MoviesRepository> { MoviesRepositoryImpl(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<MoviesService> { createService(get()) }

            single {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val okHttpBuilder = OkHttpClient.Builder()
                okHttpBuilder.readTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.writeTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.callTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.connectTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.addInterceptor(loggingInterceptor)
                if (BuildConfig.DEBUG) {
                    okHttpBuilder.addInterceptor(OkHttpProfilerInterceptor())
                }
                okHttpBuilder.addInterceptor(MoviesInterceptor(apiKey = API_KEY))
                okHttpBuilder.build()
            }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}