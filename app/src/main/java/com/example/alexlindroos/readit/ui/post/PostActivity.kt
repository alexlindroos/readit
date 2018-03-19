package com.example.alexlindroos.readit.ui.post

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import com.example.alexlindroos.readit.R
import kotlinx.android.synthetic.main.activity_post.*

/**
 * Created by Alex Lindroos on 20/02/2018.
 */
class PostActivity: Activity() {

    companion object {
        val URL = "url"
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(URL, url)
            return intent
        }
    }

    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        url = intent.getStringExtra(URL)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}