package com.kuswand.githubuser.data.remote

import com.kuswand.githubuser.domain.model.SearchResult
import com.kuswand.githubuser.domain.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{login}")
    suspend fun getUserDetail(
        @Path("login") login: String
    ): UserDetail

    @GET("search/users?per_page=10")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int
    ): SearchResult
}