package com.kuswand.githubuser.data.remote

import com.kuswand.githubuser.domain.model.SearchResult
import com.kuswand.githubuser.domain.model.User
import com.kuswand.githubuser.domain.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val token = "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"

interface ApiService {

    @Headers("Authorization: token $token")
    @GET("users/{login}")
    suspend fun getUserDetail(
        @Path("login") login: String
    ): UserDetail

    @Headers("Authorization: token $token")
    @GET("users/{login}/followers?per_page=10")
    suspend fun getUserFollowers(
        @Path("login") login: String,
        @Query("page") page: Int
    ): List<User>

    @Headers("Authorization: token $token")
    @GET("users/{login}/following?per_page=10")
    suspend fun getUserFollowing(
        @Path("login") login: String,
        @Query("page") page: Int
    ): List<User>

    @Headers("Authorization: token $token")
    @GET("search/users?per_page=10")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int
    ): SearchResult
}