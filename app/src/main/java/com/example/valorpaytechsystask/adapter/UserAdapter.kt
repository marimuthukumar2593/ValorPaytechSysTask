package com.example.valorpaytechsystask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.valorpaytechsystask.R
import com.example.valorpaytechsystask.model.alluser.UserDetails

class UserAdapter(private val mList: List<UserDetails>,val clickListenr:OnItemClicked): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.textView.text = mList[position].username
        holder.tvmail.text = mList[position].email
        holder.tvcontact.text = mList[position].phone

        holder.itemView.setOnClickListener {
            clickListenr.onClicked(position,mList[position],)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class UserViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_user)
        val textView: TextView = itemView.findViewById(R.id.tv_username)
        val tvmail: TextView = itemView.findViewById(R.id.tv_usermail)
        val tvcontact: TextView = itemView.findViewById(R.id.tv_user_contact)
    }

    interface OnItemClicked{
        fun onClicked(pos:Int,user:UserDetails)
    }
}