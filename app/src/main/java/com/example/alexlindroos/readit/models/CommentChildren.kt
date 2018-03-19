package com.example.alexlindroos.readit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Lindroos on 08/03/2018.
 */
class CommentChildren {
    @SerializedName("data")
    var data: CommentData = CommentData()
}