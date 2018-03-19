package com.example.alexlindroos.readit.network.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alex Lindroos on 07/02/2018.
 */

class APIManager {

    companion object {
        private val BASE_URL = "https://www.reddit.com/"

        fun create() : API {
            val retrofitWithOutInterceptor = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofitWithOutInterceptor.create<API>(API::class.java)
        }
    }

}