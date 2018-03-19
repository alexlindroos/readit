package com.example.alexlindroos.readit.ui.shared

import android.app.Fragment


/**
 * Created by Alex Lindroos on 13/02/2018.
 */

abstract class PresenterFragment: Fragment() {
    abstract fun attachPresenter()
    abstract fun detachPresenter()

    override fun onStart() {
        super.onStart()
        attachPresenter()
    }

    override fun onStop() {
        super.onStop()
        detachPresenter()
    }
}