package com.example.alexlindroos.readit.ui.list

import com.example.alexlindroos.readit.models.Data
import com.example.alexlindroos.readit.models.SubListData
import com.example.alexlindroos.readit.ui.shared.PresentationView

/**
 * Created by Alex Lindroos on 20/02/2018.
 */
interface SubListView: PresentationView {
    fun showError(error: String)
    fun showPosts(subList: List<SubListData>)
    fun showSubredditPosts(posts: List<Data>)
}