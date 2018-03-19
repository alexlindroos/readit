package com.example.alexlindroos.readit.network.api

import com.example.alexlindroos.readit.network.session.UserSession
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Alex Lindroos on 01/03/2018.
 */
class OAuthInterceptor: Interceptor {

    val token = UserSession.accessToken

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
                .addHeader("User-Agent", "ReadIt")
                .addHeader("Authorization","bearer " + token)
                .build()
        return chain.proceed(request)
    }

}