package com.kuswand.githubuser.presentation.search_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kuswand.githubuser.domain.model.User
import com.kuswand.githubuser.domain.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow

class SearchUserViewModel : ViewModel() {

    private var searchQuery = ""
    private var searchPagingSource: SearchPagingSource? = null
    get() {
        if (field == null || field?.invalid == true) {
            field = SearchPagingSource(searchQuery)
        }
        return field
    }

    val result: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 99),
        pagingSourceFactory = { searchPagingSource!! }
    ).flow.cachedIn(viewModelScope)

    fun searchUser(query: String) {
        searchQuery = query
        searchPagingSource?.invalidate()
    }
}