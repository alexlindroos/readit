package com.example.alexlindroos.readit.ui.comment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.Comment
import com.example.alexlindroos.readit.models.CommentChildren

/**
 * Created by Alex Lindroos on 12/03/2018.
 */

class CommentAdapter(val context: Context,
                     val list: List<CommentChildren>
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)

        holder.author.text = item.data.author
        holder.body.text = item.data.body
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val author = itemView.findViewById<TextView>(R.id.comment_author)
        val body = itemView.findViewById<TextView>(R.id.comment_body)
    }
}