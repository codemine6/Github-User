package com.kuswand.githubuser.domain.repository

import com.kuswand.githubuser.data.remote.RetrofitInstance
import com.kuswand.githubuser.domain.model.Response
import kotlinx.coroutines.flow.flow

class GithubRepository {

    private val api = RetrofitInstance.api

    fun getUserDetail(login: String) = flow {
        try {
            val response = api.getUserDetail(login)
            emit(Response.Success(response))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: ""))
        }
    }
}