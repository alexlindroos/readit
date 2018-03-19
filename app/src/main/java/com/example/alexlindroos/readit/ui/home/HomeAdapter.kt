package com.example.alexlindroos.readit.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.Data
import com.example.alexlindroos.readit.ui.shared.PicassoClient
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class HomeAdapter(val context: Context,
                  val list: List<Data>,
                  val listener: (String) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val client = PicassoClient()
    var onPressCommentClickListener: ((Data) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)

        holder.bind(item.url, listener)

        client.downloadImage(context, item.thumbnail, holder.img)

        val date = Date(item.created_utc*1000L)
        val pt = PrettyTime(Locale.ENGLISH)
        val ago = pt.format(date)

        holder.subreddit.text = "r/${item.subreddit}"
        holder.sender.text = item.author
        holder.title.text = item.title
        holder.time.text = ago
        holder.ups.text = item.ups.toString()
        holder.comments.text = item.num_comments.toString()
        holder.commentsContainer.setOnClickListener { onPressCommentClickListener?.invoke(item) }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(url: String, listener: (String) -> Unit) = with(itemView) {
            setOnClickListener { listener(url) }
        }

        val img = itemView.findViewById<ImageView>(R.id.item_image)
        val subreddit = itemView.findViewById<TextView>(R.id.item_subreddit)
        val sender = itemView.findViewById<TextView>(R.id.item_sender)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val ups = itemView.findViewById<TextView>(R.id.item_ups)
        val comments = itemView.findViewById<TextView>(R.id.item_comments)
        val time = itemView.findViewById<TextView>(R.id.item_time)
        val commentsContainer = itemView.findViewById<LinearLayout>(R.id.item_comments_container)
    }

}