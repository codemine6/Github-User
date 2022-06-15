package com.kuswand.githubuser.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kuswand.githubuser.data.remote.RetrofitInstance
import com.kuswand.githubuser.domain.model.User

class SearchPagingSource(private val query: String) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        val api = RetrofitInstance.api

        return try {
            val response = api.searchUser(query, page)
            LoadResult.Page(
                data = response.items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.items.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }
}