package com.example.alexlindroos.readit.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.models.SubListData

/**
 * Created by Alex Lindroos on 20/02/2018.
 */

class SubListAdapter(
                  val list: List<SubListData>,
                  val listener: (String) -> Unit
) : RecyclerView.Adapter<SubListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)

        holder.bind(item.display_name_prefixed, listener)

        holder.title.text = item.display_name
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_subreddit, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(url: String, listener: (String) -> Unit) = with(itemView) {
            setOnClickListener { listener(url) }
        }

        val title = itemView.findViewById<TextView>(R.id.subreddit_name)
    }
}