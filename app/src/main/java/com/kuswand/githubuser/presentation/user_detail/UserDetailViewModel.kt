package com.kuswand.githubuser.presentation.user_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuswand.githubuser.domain.model.Response
import com.kuswand.githubuser.domain.model.UserDetail
import com.kuswand.githubuser.domain.repository.GithubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val login = savedStateHandle.get<String>("LOGIN")!!
    private val repository = GithubRepository()

    private val _user = MutableStateFlow(UserDetail())
    val user = _user.asStateFlow()

    init {
        getUserDetail(login)
    }

    private fun getUserDetail(login: String) {
        viewModelScope.launch {
            repository.getUserDetail(login).collect { result ->
                if (result is Response.Success) {
                    _user.value = result.data
                }
            }
        }
    }
}