package com.example.alexlindroos.readit.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alex Lindroos on 01/03/2018.
 */
class APIManagerWithInterceptors{

    companion object {
        private val BASE_URL = "https://oauth.reddit.com/"
        val client = OkHttpClient.Builder().addInterceptor(OAuthInterceptor())

        fun create() : API {
            val retrofitWithInterceptor = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofitWithInterceptor.create<API>(API::class.java)
        }
    }
}
