package com.example.alexlindroos.readit.ui.comment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.CommentChildren
import com.example.alexlindroos.readit.network.api.APIManager
import com.example.alexlindroos.readit.ui.shared.PresenterActivity
import com.example.alexlindroos.readit.ui.shared.SimpleDividerItemDecoration
import com.example.alexlindroos.readit.util.hide
import com.example.alexlindroos.readit.util.show
import kotlinx.android.synthetic.main.activity_comment.*
import org.jetbrains.anko.toast

/**
 * Created by Alex Lindroos on 05/03/2018.
 */
class CommentActivity: PresenterActivity(), CommentView {

    companion object {
        val ARTICLE_ID = "article_id"
        val SUBREDDIT = "subReddit"
        fun newIntent(context: Context, articleId: String, subReddit: String): Intent {
            val intent = Intent(context, CommentActivity::class.java)
            intent.putExtra(ARTICLE_ID, articleId)
            intent.putExtra(SUBREDDIT, subReddit)
            return intent
        }
    }

    private var presenter = CommentPresenter(this, APIManager.create())
    private var articleId = ""
    private var subReddit = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        articleId = intent.getStringExtra(ARTICLE_ID) as String
        subReddit = intent.getStringExtra(SUBREDDIT) as String

        setupRecyclerView()
        setupView()
    }

    override fun attachPresenter() {
        presenter.attachView(this)
        presenter.fetchComments(articleId, subReddit)
    }

    override fun detachPresenter() {
        presenter.detachView()
    }

   override fun showComments(commentData: List<CommentChildren>) {
        initializeContent(commentData)
    }

    override fun showError(error: String) {
        println(error)
    }

    private fun setupRecyclerView() {
        rv_home.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_home.addItemDecoration(SimpleDividerItemDecoration(this))
    }

    private fun initializeContent(commentData: List<CommentChildren>) {
        val adapter = CommentAdapter(this, commentData)
        rv_home.adapter = adapter
        adapter.notifyDataSetChanged()
        rv_home.show()
        progressBar1.hide()
    }

    private fun setupView() {
        rv_home.hide()
        progressBar1.show()
        progressBar1.isIndeterminate = true
    }

}