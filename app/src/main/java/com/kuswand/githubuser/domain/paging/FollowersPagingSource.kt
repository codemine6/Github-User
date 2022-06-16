package com.kuswand.githubuser.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kuswand.githubuser.data.remote.RetrofitInstance
import com.kuswand.githubuser.domain.model.User

class FollowersPagingSource(val login: String) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        val api = RetrofitInstance.api

        return try {
            val response = api.getUserFollowers(login, page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }
}