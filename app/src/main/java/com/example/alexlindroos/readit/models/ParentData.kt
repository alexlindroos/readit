package com.example.alexlindroos.readit.models

import com.example.alexlindroos.readit.models.SubListData
import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Lindroos on 20/02/2018.
 */

class ParentData{
    @SerializedName("children")
    var children: List<ChildData> = listOf()
}