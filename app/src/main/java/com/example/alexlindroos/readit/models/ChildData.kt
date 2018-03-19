package com.example.alexlindroos.readit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Lindroos on 20/02/2018.
 */
class ChildData{
    @SerializedName("data")
    var data: SubListData = SubListData()
}