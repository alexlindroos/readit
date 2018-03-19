package com.example.alexlindroos.readit.ui.home

import com.example.alexlindroos.readit.network.api.API
import com.example.alexlindroos.readit.ui.shared.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Lindroos on 08/02/2018.
 */

class HomePresenter(private val view: HomeView,
                    private val api: API
) : BasePresenter<HomeView>() {

    fun fetchRedditPosts(subReddit: String) {
        api.getSubredditsPosts(subReddit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val allData = result.allData
                            val allChildrens = allData.children.map { it }
                            val allChildrensData = allChildrens.map { it.data }
                            view.showPosts(allChildrensData)
                        },
                        { error ->
                            view.showError(error.toString())
                        }
                )
    }

    fun fetchHotRedditPosts(subReddit: String) {
        api.getSubredditsHotPosts("r/$subReddit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val allData = result.allData
                            val allChildrens = allData.children.map { it }
                            val allChildrensData = allChildrens.map { it.data }
                            view.showPosts(allChildrensData)
                        },
                        { error ->
                            view.showError(error.toString())
                        }
                )
    }

}