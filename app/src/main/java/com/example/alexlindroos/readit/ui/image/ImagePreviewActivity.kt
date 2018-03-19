package com.example.alexlindroos.readit.ui.image

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.alexlindroos.readit.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_preview.*

/**
 * Created by Alex Lindroos on 23/02/2018.
 */

class ImagePreviewActivity: Activity() {

    companion object {
        val URL = "url"
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, ImagePreviewActivity::class.java)
            intent.putExtra(URL, url)
            return intent
        }
    }

    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)
        url = intent.getStringExtra(URL)
        Picasso.with(this).load(url).into(image_preview)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}