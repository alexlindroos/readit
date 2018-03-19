package com.example.alexlindroos.readit.ui.signup

import android.app.Activity
import android.os.Bundle
import com.example.alexlindroos.readit.R
import kotlinx.android.synthetic.main.activity_signup.*

/**
 * Created by Alex Lindroos on 05/02/2018.
 */
class SignupActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val url = "http://www.reddit.com/register"
        webview.loadUrl(url)
    }

    override fun onResume() {
        super.onResume()
        finish()
    }
}