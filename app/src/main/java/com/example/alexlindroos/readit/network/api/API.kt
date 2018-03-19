package com.example.alexlindroos.readit.network.api

import com.example.alexlindroos.readit.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Alex Lindroos on 05/02/2018.
 */

interface API {

    @GET("{subReddit}.json")
    fun getSubredditsPosts(@Path("subReddit") subReddit: String): Observable<SubRedditResponse>

    @GET("{subReddit}/new.json?sort=hot")
    fun getSubredditsHotPosts(@Path("subReddit") subReddit: String): Observable<SubRedditResponse>

    @GET("subreddits/mine/subscriber.json")
    fun getSubscribedSubreddits(): Observable<SubscribedSubredditsResponse>

    @GET("api/v1/me")
    fun getCurrentUserInfo(): Observable<UserInfoData>

    @GET("r/{subReddit}/comments/{articleId}.json?sort=best&depth=1&limit=25")
    fun getComments(@Path("subReddit") subReddit: String, @Path("articleId") articleId: String): Observable<List<Comment>>
}