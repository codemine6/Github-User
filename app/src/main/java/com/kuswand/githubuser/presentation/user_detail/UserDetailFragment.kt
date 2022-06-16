package com.kuswand.githubuser.presentation.user_detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kuswand.githubuser.R
import com.kuswand.githubuser.databinding.FragmentUserDetailBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.user.collect { user ->
                binding.apply {
                    Glide.with(this.root)
                        .load(user.avatarUrl)
                        .into(civAvatar)

                    tvFollowers.text = user.followers.toString()
                    tvFollowing.text = user.following.toString()
                    tvLogin.text = user.login
                    mbRepositories.text = getString(R.string.repositories, user.publicRepos)
                }

                binding.mbShare.setOnClickListener {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, user.url)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(sendIntent, null))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}