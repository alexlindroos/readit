package com.example.alexlindroos.readit.ui.profile

import com.example.alexlindroos.readit.network.api.API
import com.example.alexlindroos.readit.ui.shared.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Lindroos on 22/02/2018.
 */

class ProfilePresenter(private val view: ProfileView,
                    private val api: API
) : BasePresenter<ProfileView>() {

    fun fetchCurrentUserInfo() {
        api.getCurrentUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            view.showInfo(result)
                        },
                        { error ->
                            view.showError(error.toString())
                        }
                )
    }
}