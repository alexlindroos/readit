package com.example.alexlindroos.readit.ui.comment

import com.example.alexlindroos.readit.models.CommentChildren
import com.example.alexlindroos.readit.models.CommentData
import com.example.alexlindroos.readit.models.Data
import com.example.alexlindroos.readit.ui.shared.PresentationView

/**
 * Created by Alex Lindroos on 08/03/2018.
 */
interface CommentView: PresentationView {
    fun showComments(commentData: List<CommentChildren>)
    fun showError(error: String)
}