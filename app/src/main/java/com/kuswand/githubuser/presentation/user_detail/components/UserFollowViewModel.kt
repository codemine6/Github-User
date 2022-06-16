package com.kuswand.githubuser.presentation.user_detail.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kuswand.githubuser.domain.model.User
import com.kuswand.githubuser.domain.paging.FollowersPagingSource
import com.kuswand.githubuser.domain.paging.FollowingPagingSource
import kotlinx.coroutines.flow.Flow

class UserFollowViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val login = savedStateHandle.get<String>("LOGIN")!!

    val followers: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 99),
        pagingSourceFactory = { FollowersPagingSource(login) }
    ).flow.cachedIn(viewModelScope)

    val following: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 99),
        pagingSourceFactory = { FollowingPagingSource(login) }
    ).flow.cachedIn(viewModelScope)
}