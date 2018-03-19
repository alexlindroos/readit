package com.example.alexlindroos.readit.ui.comment

import com.example.alexlindroos.readit.network.api.API
import com.example.alexlindroos.readit.ui.shared.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Lindroos on 08/03/2018.
 */

class CommentPresenter(private val view: CommentView,
                       private val api: API
): BasePresenter<CommentView>() {

    fun fetchComments(articleId: String, subReddit: String) {
        api.getComments(subReddit, articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val allData = result[1]
                            val comments = allData.commentAllData.children
                            view.showComments(comments)
                        },
                        { error ->
                            view.showError(error.toString())
                        }
                )
    }
}