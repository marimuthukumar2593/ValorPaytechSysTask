package com.example.valorpaytechsystask.retrofit

import com.example.valorpaytechsystask.model.alluser.UserDetails
import com.example.valorpaytechsystask.model.comments.Comments
import com.example.valorpaytechsystask.model.posts.PostData
import com.example.valorpaytechsystask.model.posts.PostDetails
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface APICallsInterface {

    @GET("/users")
    fun getAllUsers() : Observable<List<UserDetails>>

    @GET("/users/{id}")
    fun getUser(@Path("id") id:String) : Observable<List<UserDetails>>

    @GET("/users/{id}/posts")
    fun getUserPost(@Path("id") id:String ) : Observable<List<PostDetails>>

//    @GET("/users/{id}/posts")
//    fun getUserPost(@Path("id") id:String ) : Single<List<PostDetails>>

    @GET("/post/{id}/comments")
    fun getCommentsByPost(@Path("id") id:String) : Observable<List<Comments>>

}