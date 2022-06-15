package com.kuswand.githubuser.presentation.search_user

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuswand.githubuser.databinding.FragmentSearchUserBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchUserFragment : Fragment() {

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchUserViewModel by viewModels()
    var searchQuery = ""
    val handler = Handler(Looper.getMainLooper())
    val searchRunnable = Runnable {
        viewModel.searchUser(searchQuery)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPagingAdapter = UserPagingAdapter()
        binding.rvResult.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userPagingAdapter
        }

        lifecycleScope.launch {
            viewModel.result.collectLatest { result ->
                userPagingAdapter.submitData(result)
            }
        }

        binding.svUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchQuery = newText
                    handler.removeCallbacks(searchRunnable)
                    handler.postDelayed(searchRunnable, 500)
                }
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}