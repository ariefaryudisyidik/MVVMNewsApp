package com.androiddevs.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBreakingNewsBinding.bind(view)

        setupRecyclerView()
        getBreakingNews()
        navigateToArticle()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.adapter = newsAdapter
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
                        Log.d("TAG", "getBreakingNews: $it")
                        newsAdapter.differ.submitList(it.articles)
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
        message?.toast(requireContext())
    }

    private fun showLoading(visible: Boolean) {
        binding.paginationProgressBar.isVisible = visible
    }

    private fun navigateToArticle() {
        newsAdapter.setOnItemClickListener {
            findNavController().navigate(BreakingNewsFragmentDirections.toArticleFragment(it))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}