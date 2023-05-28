package com.example.valorpaytechsystask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.valorpaytechsystask.R
import com.example.valorpaytechsystask.model.comments.Comments
import com.example.valorpaytechsystask.model.posts.PostDetails

class CommentsAdapter(private var mList: ArrayList<Comments>) :RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {


    inner class CommentsViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val username: TextView = itemView.findViewById(R.id.tv_comments_username)
        val tvcomments: TextView = itemView.findViewById(R.id.tv_comments_desc)
        val imguser: ImageView = itemView.findViewById(R.id.img_commentsuser)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comments_item, parent, false)
        return CommentsViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
       val user = mList[position].name
        val desc = mList[position].body

        holder.username.text=user
        holder.tvcomments.text=desc

    }
}