package com.example.alexlindroos.readit.ui.home

import com.example.alexlindroos.readit.models.Data
import com.example.alexlindroos.readit.ui.shared.PresentationView

/**
 * Created by Alex Lindroos on 08/02/2018.
 */
interface HomeView : PresentationView {
    fun showError(error: String)
    fun showPosts(posts: List<Data>)
}