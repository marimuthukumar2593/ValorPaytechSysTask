package com.example.valorpaytechsystask.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valorpaytechsystask.R
import com.example.valorpaytechsystask.model.comments.Comments
import com.example.valorpaytechsystask.model.posts.PostDetails
import com.example.valorpaytechsystask.ui.home.HomeViewModel
import com.example.valorpaytechsystask.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostViewAdapter(private var mList: ArrayList<PostDetails>, viewmodel: HomeViewModel,val listener:OnPostItemClicked ) :RecyclerView.Adapter<PostViewAdapter.PostViewHolder>(){

    var homeViewModel:HomeViewModel =viewmodel
    inner class PostViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_post)
        val tvupdated: TextView = itemView.findViewById(R.id.tv_updated)
        val tvcomments: TextView = itemView.findViewById(R.id.tv_post_comments)
        val tvrefresh: TextView = itemView.findViewById(R.id.tv_refresh)
        val imgclose: ImageButton = itemView.findViewById(R.id.img_close)
//        val commentsreccylerview: RecyclerView = itemView.findViewById(R.id.comments_recyclerview)

    }

    interface OnPostItemClicked{
        fun onPostClicked(pos:Int,user:PostDetails,list: ArrayList<Comments>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewAdapter.PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var imgsrc = "https://i.imgur.com/bZDLmPw.jpg"
        var updated ="Updated : 1 minutes ago"

        if(position%2==0){
            imgsrc ="https://i.imgur.com/L0X8CWb.jpg"
             updated ="Updated : 2 minutes ago"
        }
        Glide.with( holder.imageView.context)
            .load(imgsrc)
        .into( holder.imageView)
        holder.tvupdated.text = updated

        fetchComments(position,mList[position].id!!,holder.imageView.context,holder.tvcomments,false)

        holder.tvrefresh.setOnClickListener {
            if(Constants.checkForInternet( holder.tvcomments.context)) {
                reloadPost(mList[position].userId!!)
            }
            else{
                showNoInternetAlert(position,"Please check your internet connection",holder.tvcomments.context,holder.tvcomments)
            }
        }


        holder.tvcomments.setOnClickListener {
            if(Constants.checkForInternet( holder.tvcomments.context)) {
                fetchComments(
                    position,
                    mList[position].id!!,
                    holder.imageView.context,
                    holder.tvcomments,
                    true
                )
            }
            else{
                showNoInternetAlert(position,"Please check your internet connection",holder.tvcomments.context,holder.tvcomments)
            }

        }
    }

    fun showNoInternetAlert(position: Int, msg: String, context: Context, tvcomments: TextView) {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setNegativeButton("Ok"){dialogInterface, which ->
            dialogInterface.dismiss()
        }

        // create dialog box
        // set title for alert dialog box
        val alert = dialogBuilder.create()
        alert.setTitle("No Internet Connection")
        alert.setMessage(msg)
        alert.show()
    }


    override fun getItemCount(): Int {
       return mList.size
    }

    fun fetchComments(pos:Int,
        postid: String,
        context: Context,
        tvcomments: TextView,isclicked:Boolean) {
        homeViewModel.fetchCommentsById(postid)
            .observeOn(AndroidSchedulers.mainThread()) // list of challenges which contain challenge ID
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
//                    Toast.makeText(context,"Comments ===${it.size}",Toast.LENGTH_SHORT).show()
                    tvcomments.text= "Comments :${it.size}"

                    if(isclicked){
                        listener.onPostClicked(pos,mList[pos],it as ArrayList<Comments>)
                    }

                }
            )
    }

    fun reloadPost(userid:String) {
        homeViewModel.fetchAllPosts(userid)
            .observeOn(AndroidSchedulers.mainThread()) // list of challenges which contain challenge ID
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    refreshView(it as ArrayList)
                }
            )
    }

    private fun refreshView(postDetails: ArrayList<PostDetails>){
        mList.clear()
        mList.addAll(postDetails)
        notifyDataSetChanged()
    }


}