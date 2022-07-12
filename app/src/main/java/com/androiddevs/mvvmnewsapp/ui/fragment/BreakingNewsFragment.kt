package com.androiddevs.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.androiddevs.mvvmnewsapp.ui.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel
import com.androiddevs.mvvmnewsapp.utils.Extension.toast
import com.androiddevs.mvvmnewsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBreakingNewsBinding.bind(view)

        getBreakingNews()
    }

    private fun getBreakingNews() {
        viewModel.getBreakingNews().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    result.data?.let {
                        val newsAdapter = NewsAdapter()
                        newsAdapter.differ.submitList(it.articles)
                        binding.rvBreakingNews.adapter = newsAdapter
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showMessage(result.message)
                }
            }
        }
    }

    private fun showMessage(message: String?) {
        requireContext().toast(message)
    }

    private fun showLoading(visible: Boolean) {
        binding.paginationProgressBar.isVisible = visible
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}