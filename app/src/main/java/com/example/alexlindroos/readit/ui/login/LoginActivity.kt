package com.example.alexlindroos.readit.ui.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.network.session.UserSession
import com.example.alexlindroos.readit.ui.main.MainActivity
import com.example.alexlindroos.readit.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import org.jetbrains.anko.startActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * Created by Alex Lindroos on 02/02/2018.
 */

class LoginActivity: Activity() {

    private val CLIENT_ID = ""
    private val REDIRECT_URI = "http://www.example.com/my_redirect"
    private val STATE = "LOGIN"
    private val SCOPE = "identity mysubreddits read"
    private val ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token"

    private val AUTH_URL = "https://www.reddit.com/api/v1/authorize.compact?client_id=$CLIENT_ID" +
            "&response_type=code&state=$STATE&redirect_uri=$REDIRECT_URI&" +
            "duration=permanent&scope=$SCOPE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        UserSession.initialize(this)

        login_button.setOnClickListener {
            val url = AUTH_URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        signup_button.setOnClickListener {
            startActivity<SignupActivity>()
        }
    }

    override fun onResume() {
        super.onResume()

        if (UserSession.isOpen) {
            startActivity<MainActivity>()
        } else {
            if (intent != null && intent.action == Intent.ACTION_VIEW) {
                val uri = intent.data
                if (uri.getQueryParameter("error") != null) {
                    val error = uri.getQueryParameter("error")
                    Log.e("TAG", "Error: " + error)
                } else {
                    val state = uri.getQueryParameter("state")
                    if (state == STATE) {
                        val code = uri.getQueryParameter("code")
                        getAccessToken(code)
                    }
                }
            }
        }
    }

     private fun getAccessToken(code: String) {
        val client = OkHttpClient()
        val authString = CLIENT_ID + ":"
        val encodedAuthString = Base64.encodeToString(authString.toByteArray(),
                Base64.NO_WRAP)
        val grant = "grant_type=authorization_code&code=$code&redirect_uri=$REDIRECT_URI"

        val request = Request.Builder()
                .addHeader("User-Agent", "ReadIt")
                .addHeader("Authorization", "Basic " + encodedAuthString)
                .url(ACCESS_TOKEN_URL)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                        grant))
                .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("TAG", "ERROR: " + e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                Log.d("TAG", json)

                val data: JSONObject?
                try {
                    data = JSONObject(json)
                    val accessToken = data.optString("access_token")
                    val refreshToken = data.optString("refresh_token")

                    UserSession.open(accessToken, refreshToken)
                    finish()
                    startActivity<MainActivity>()

                    Log.d("TAG", "Access Token = " + accessToken)
                    Log.d("TAG", "Refresh Token = " + refreshToken)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })
    }
}

