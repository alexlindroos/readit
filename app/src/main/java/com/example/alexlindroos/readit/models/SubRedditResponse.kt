package com.example.alexlindroos.readit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Lindroos on 07/02/2018.
 */

class SubRedditResponse {
    @SerializedName("data")
    var allData: AllData = AllData()
}