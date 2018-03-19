package com.example.alexlindroos.readit.ui.shared

/**
 * Created by Alex Lindroos on 08/02/2018.
 */

abstract class BasePresenter<in T: PresentationView> {

    private var presentationView: T? = null

    open fun attachView(presentationView: T) {
        this.presentationView = presentationView
    }

    open fun detachView() {
        presentationView = null
    }

    fun isViewAttached(): Boolean = presentationView != null
}