package com.example.valorpaytechsystask.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valorpaytechsystask.model.alluser.UserDetails
import com.example.valorpaytechsystask.model.comments.Comments
import com.example.valorpaytechsystask.model.posts.PostData
import com.example.valorpaytechsystask.model.posts.PostDetails
import com.example.valorpaytechsystask.retrofit.RetrofitAPI
import io.reactivex.Observable
import io.reactivex.Single

class HomeViewModel : ViewModel() {


    private var allusers = MutableLiveData<List<UserDetails>>()
    private var allposts = MutableLiveData<List<PostDetails>>()
    private var commentsByPost = MutableLiveData<List<Comments>>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    fun observeUserdetails() : LiveData<List<UserDetails>> {
        return allusers
    }

    fun fetchAllusers(): Observable<List<UserDetails>> {
        return RetrofitAPI.api.getAllUsers()
    }


    fun fetchAllPosts(userid:String): Observable<List<PostDetails>> {
        return RetrofitAPI.api.getUserPost(userid)
    }

        fun fetchCommentsById(postid:String): Observable<List<Comments>> {
        return RetrofitAPI.api.getCommentsByPost(postid)
    }

//    fun fetchuser(userid:String): Observable<List<PostDetails>> {
//        return RetrofitAPI.api.getUserPost(userid)
//    }



//    fun fetchAllPosts(userid:String):Single<List<PostDetails>> {
//        return RetrofitAPI.api.getUserPost(userid)
//    }

//    fun fetchCommentsById(postid:String): Single<List<Comments>> {
//        return RetrofitAPI.api.getCommentsByPost(postid)
//    }


}