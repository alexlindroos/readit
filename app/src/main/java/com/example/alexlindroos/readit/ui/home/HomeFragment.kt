package com.example.alexlindroos.readit.ui.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.Data
import com.example.alexlindroos.readit.network.api.APIManager
import com.example.alexlindroos.readit.ui.comment.CommentActivity
import com.example.alexlindroos.readit.ui.image.ImagePreviewActivity
import com.example.alexlindroos.readit.ui.login.LoginActivity
import com.example.alexlindroos.readit.ui.post.PostActivity
import com.example.alexlindroos.readit.ui.shared.PresenterFragment
import com.example.alexlindroos.readit.ui.shared.SimpleDividerItemDecoration
import com.example.alexlindroos.readit.util.hide
import com.example.alexlindroos.readit.util.show
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class HomeFragment: PresenterFragment(), HomeView {

    private var subReddit = "all"
    private var postIsOpened = false
    private val QUERY = "query"
    private var query = ""
    private var presenter = HomePresenter(this, APIManager.create())

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupRecyclerView()

        if (arguments != null) {
            query = arguments.getString(QUERY)
            presenter.fetchHotRedditPosts(query)
            rv_header.text = query
        }
    }

    override fun attachPresenter() {
        presenter.attachView(this)
       if (!postIsOpened && arguments == null) {
            presenter.fetchRedditPosts("r/$subReddit")
        }
    }

    override fun detachPresenter() {
        presenter.detachView()
    }

    override fun showError(error: String) {
        println(error)
    }

    override fun showPosts(posts: List<Data>) {
        initializeContent(posts)
    }

    private fun initializeContent(posts: List<Data>) {
        rv_home.show()
        progressBar1.hide()

        if (posts.isEmpty()) {
            progressBar1.hide()
            toast(R.string.error_search)
        }

        val adapter = HomeAdapter(context, posts) {
            openPostActivity(it)
            println(it)
        }

        with(adapter) {
            onPressCommentClickListener = { commentClicked(it) }
        }

        rv_home.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setupView() {
        rv_home.hide()
        progressBar1.show()
        progressBar1.isIndeterminate = true

        rv_header.text = subReddit
        postIsOpened = false
    }

    private fun setupRecyclerView() {
        rv_home.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_home.addItemDecoration(SimpleDividerItemDecoration(context))
    }

    private fun openPostActivity(url: String) {
        if (url.contains("i.redd.it")) {
            val intent = ImagePreviewActivity.newIntent(activity, url)
            postIsOpened = true
            startActivity(intent)
        } else {
            val intent = PostActivity.newIntent(activity, url)
            postIsOpened = true
            startActivity(intent)
        }
    }

    private fun commentClicked(data: Data) {
        val articleId = data.id
        val subreddit = data.subreddit
        val intent = CommentActivity.newIntent(context, articleId, subreddit)
        startActivity(intent)
    }
}