package com.kuswand.githubuser.domain.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val login: String = "",
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
    val url: String = ""
)