package com.kuswand.githubuser.presentation.user_detail.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class UserFollowPagerAdapter(
    fragment: Fragment,
    val login: String
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = UserFollowFragment()
        fragment.arguments = Bundle().apply {
            putInt("TYPE", position)
            putString("LOGIN", login)
        }
        return fragment
    }
}