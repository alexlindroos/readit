package com.example.alexlindroos.readit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class SubscribedSubredditsResponse{
    @SerializedName("data")
    var parentData: ParentData = ParentData()
}
