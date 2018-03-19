package com.example.alexlindroos.readit.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.UserInfoData
import com.example.alexlindroos.readit.network.api.APIManager
import com.example.alexlindroos.readit.network.api.APIManagerWithInterceptors
import com.example.alexlindroos.readit.network.session.UserSession
import com.example.alexlindroos.readit.ui.login.LoginActivity
import com.example.alexlindroos.readit.ui.shared.PresenterFragment
import com.example.alexlindroos.readit.util.format
import com.example.alexlindroos.readit.util.hide
import com.example.alexlindroos.readit.util.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.*
import java.util.*

/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class ProfileFragment: PresenterFragment(), ProfileView {

    private val presenter = ProfilePresenter(this, APIManagerWithInterceptors.create())

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener {
            logoutClicked()
        }
        setupView()
    }

    override fun attachPresenter() {
        presenter.attachView(this)
        presenter.fetchCurrentUserInfo()
    }

    override fun detachPresenter() {
        presenter.detachView()
    }


    @SuppressLint("SetTextI18n")
    override fun showInfo(info: UserInfoData) {
        profile_progressbar.hide()

        val comment_karma = info.comment_karma.toString()
        val link_karma = info.link_karma.toString()
        val gold = info.gold_creddits.toString()
        val date = Date(info.created_utc*1000L)
        val verified_text = if (!info.verified) getString(R.string.is_not_verified) else getString(R.string.is_verified)

        profile_name.text = info.name
        profile_comment_karma.text = "${resources.getString(R.string.comment_karma)} $comment_karma"
        profile_link_karma.text = "${resources.getString(R.string.link_karma)} $link_karma"
        profile_gold.text = "${resources.getString(R.string.gold)} $gold"
        profile_created.text = "${resources.getString(R.string.created)} ${date.format(context)}"
        profile_verified.text = "${resources.getString(R.string.verified)} $verified_text"
        Picasso.with(context).load(info.icon_img).into(profile_pic)
    }


    override fun showError(error: String) {
        println(error)
    }

    private fun logoutClicked() {
        alert(getString(R.string.logout_alert)) {
            yesButton {
                UserSession.close()
                startActivity(intentFor<LoginActivity>().newTask())
            }
            noButton {}
        }.show()
    }

    private fun setupView() {
        profile_progressbar.show()
        profile_progressbar.isIndeterminate = true
    }
}