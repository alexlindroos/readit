package com.example.alexlindroos.readit.network.session

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import org.jetbrains.anko.defaultSharedPreferences

@SuppressLint("StaticFieldLeak")
/**
 * Created by Alex Lindroos on 05/02/2018.
 */

object UserSession {

    val access_token = "access_token"
    val refresh_token = "refresh_token"

    private lateinit var context: Context

    var accessToken: String? = null
        get() {
            if (field == null) {
                field = context.defaultSharedPreferences.getString(access_token, null)
            }
            return field
        }
        private set(value) {
            field = value
            context.defaultSharedPreferences.edit().putString(access_token, value).apply()
        }

    var refreshToken: String? = null
        get() {
            if (field == null) {
                field = context.defaultSharedPreferences.getString(refresh_token, null)
            }
            return field
        }
        private set(value) {
            field = value
            context.defaultSharedPreferences.edit().putString(refresh_token, value).apply()
        }


    val isOpen: Boolean
        get() = !TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(refreshToken)

    fun initialize(context: Context) {
        this.context = context
    }

    fun open(accessToken: String, refreshToken: String) {
        UserSession.accessToken = accessToken
        UserSession.refreshToken = refreshToken
    }

    fun close() {
        if (!isOpen) {
            return
        }

        accessToken = null
        refreshToken = null
    }
}