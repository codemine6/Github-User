package com.kuswand.githubuser.presentation.user_detail.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuswand.githubuser.R
import com.kuswand.githubuser.databinding.FragmentUserFollowBinding
import com.kuswand.githubuser.presentation.search_user.UserPagingAdapter
import com.kuswand.githubuser.presentation.user_detail.UserDetailFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserFollowFragment : Fragment() {

    private var _binding: FragmentUserFollowBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserFollowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val followersAdapter = UserPagingAdapter {
            val fragment = UserDetailFragment()
            fragment.arguments = Bundle().apply {
                putString("LOGIN", it.login)
            }

            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = followersAdapter
        }
        lifecycleScope.launch {
            viewModel.followers.collectLatest { result ->
                followersAdapter.submitData(result)
            }
        }

        val followingAdapter = UserPagingAdapter {
            val fragment = UserDetailFragment()
            fragment.arguments = Bundle().apply {
                putString("LOGIN", it.login)
            }

            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }
        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = followingAdapter
        }
        lifecycleScope.launch {
            viewModel.following.collectLatest { result ->
                followingAdapter.submitData(result)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        arguments?.getInt("TYPE", 0).let { type ->
            when (type) {
                0 -> {
                    binding.rvFollowers.isVisible = true
                    binding.rvFollowing.isVisible = false
                }
                1 -> {
                    binding.rvFollowers.isVisible = false
                    binding.rvFollowing.isVisible = true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}