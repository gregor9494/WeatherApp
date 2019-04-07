package com.example.grzesiek.myapplication.di.koin

import com.example.grzesiek.myapplication.BuildConfig
import com.example.grzesiek.myapplication.api.Const
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

val applicationModule = module {
    single<Retrofit> { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
            .baseUrl(Const.PATH)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }

    okHttpClientBuilder.addInterceptor(createKeyInterceptor())

    return okHttpClientBuilder.build()
}

fun createKeyInterceptor(): (Interceptor.Chain) -> Response {
    return {
        val original = it.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("APPID", Const.APIKEY)
                .addQueryParameter("units", "metric")
                .addQueryParameter("lang", Locale.getDefault().language)
                .build()

        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        it.proceed(request)
    }
}
