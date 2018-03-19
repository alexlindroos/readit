package com.example.alexlindroos.readit.ui.list

import com.example.alexlindroos.readit.network.api.API
import com.example.alexlindroos.readit.ui.shared.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Lindroos on 20/02/2018.
 */

class SubListPresenter(private val view: SubListView,
                       private val api: API,
                       private val apiWithoutInterceptors: API
) : BasePresenter<SubListView>() {

    fun fetchSubscribedSubreddits() {
        api.getSubscribedSubreddits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val allData = result.parentData
                            val allChildrens = allData.children.map { it }
                            val allChildrensData = allChildrens.map { it.data }
                            view.showPosts(allChildrensData)
                        },
                        { error ->
                            view.showError(error.toString())
                        }
                )
    }

    fun fetchRedditPosts(subReddit: String) {
        apiWithoutInterceptors.getSubredditsPosts(subReddit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val allData = result.allData
                            val allChildrens = allData.children.map { it }
                            val allChildrensData = allChildrens.map { it.data }
                            view.showSubredditPosts(allChildrensData)
                        },
                        { error ->
                            view.showError(error.toString())
                        }
                )
    }

}