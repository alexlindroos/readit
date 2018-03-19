package com.example.alexlindroos.readit.ui.shared

import android.content.Context
import android.widget.ImageView
import com.example.alexlindroos.readit.R
import com.squareup.picasso.Picasso

/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class PicassoClient {

    fun downloadImage(context: Context, url: String?, img: ImageView) {
        if (url != null && url.isNotEmpty()) {
            Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(img)
        }else{
            Picasso.with(context).load(R.drawable.placeholder).into(img)
        }
    }
}