package com.example.alexlindroos.readit.ui.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.Data
import com.example.alexlindroos.readit.models.SubListData
import com.example.alexlindroos.readit.network.api.APIManager
import com.example.alexlindroos.readit.network.api.APIManagerWithInterceptors
import com.example.alexlindroos.readit.ui.comment.CommentActivity
import com.example.alexlindroos.readit.ui.home.HomeAdapter
import com.example.alexlindroos.readit.ui.image.ImagePreviewActivity
import com.example.alexlindroos.readit.ui.post.PostActivity
import com.example.alexlindroos.readit.ui.shared.PresenterFragment
import com.example.alexlindroos.readit.ui.shared.SimpleDividerItemDecoration
import com.example.alexlindroos.readit.util.hide
import com.example.alexlindroos.readit.util.show
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class SubListFragment : PresenterFragment(), SubListView {

    private val presenter = SubListPresenter(this, APIManagerWithInterceptors.create(), APIManager.create())
    private var subreddit_title = ""
    private var activityOpen = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityOpen = false
        setupView()
        setupRecyclerView()
    }

    override fun attachPresenter() {
        presenter.attachView(this)
        if (!activityOpen) {
            presenter.fetchSubscribedSubreddits()
        }
    }

    override fun detachPresenter() {
        presenter.detachView()
    }

    override fun showError(error: String) {
        println(error)
    }

    override fun showPosts(subList: List<SubListData>) {
        initializeContent(subList)
    }

    override fun showSubredditPosts(posts: List<Data>) {
        initializeCertainSubredditContent(posts)
    }

    private fun initializeContent(subList: List<SubListData>) {
        val adapter = SubListAdapter(subList) {
            openSubReddit(it)
            println(it)
        }
        rv_home.adapter = adapter
        adapter.notifyDataSetChanged()
        rv_home.show()
        progressBar1.hide()
    }

    private fun initializeCertainSubredditContent(posts: List<Data>) {
        val adapter = HomeAdapter(context, posts) {
            openPostActivity(it)
            println(it)
        }

        with(adapter) {
            onPressCommentClickListener = { commentClicked(it) }
        }

        rv_header.text = subreddit_title
        rv_home.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun openSubReddit(name: String) {
        presenter.fetchRedditPosts(name)
        subreddit_title = name
    }

    private fun openPostActivity(url: String) {
        if (url.contains("i.redd.it")) {
            val intent = ImagePreviewActivity.newIntent(activity, url)
            activityOpen = true
            startActivity(intent)
        } else {
            val intent = PostActivity.newIntent(activity, url)
            activityOpen = true
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        rv_home.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_home.addItemDecoration(SimpleDividerItemDecoration(context))
    }

    private fun setupView() {
        rv_home.hide()
        progressBar1.show()
        progressBar1.isIndeterminate = true
    }

    private fun commentClicked(data: Data) {
        val articleId = data.id
        val subreddit = data.subreddit
        val intent = CommentActivity.newIntent(context, articleId, subreddit)
        activityOpen = true
        startActivity(intent)
    }
}