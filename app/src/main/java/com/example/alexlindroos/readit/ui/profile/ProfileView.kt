package com.example.alexlindroos.readit.ui.profile

import com.example.alexlindroos.readit.models.UserInfoData
import com.example.alexlindroos.readit.ui.shared.PresentationView

/**
 * Created by Alex Lindroos on 22/02/2018.
 */
interface ProfileView: PresentationView {
    fun showInfo(info: UserInfoData)
    fun showError(error: String)
}