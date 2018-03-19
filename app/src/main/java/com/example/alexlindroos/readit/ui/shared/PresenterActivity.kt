package com.example.alexlindroos.readit.ui.shared

import android.support.v7.app.AppCompatActivity

/**
 * Created by Alex Lindroos on 08/02/2018.
 */

abstract class PresenterActivity: AppCompatActivity() {
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