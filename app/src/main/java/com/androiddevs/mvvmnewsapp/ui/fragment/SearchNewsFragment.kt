package com.androiddevs.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentSearchNewsBinding
import com.androiddevs.mvvmnewsapp.ui.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel
import com.androiddevs.mvvmnewsapp.utils.Constants.ARTICLE
import com.androiddevs.mvvmnewsapp.utils.Extension.toast
import com.androiddevs.mvvmnewsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchNewsBinding.bind(view)

        setupRecyclerView()
        searchNews()
        navigateToArticle()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.adapter = newsAdapter
    }

    private fun searchNews() {
        binding.etSearch.addTextChangedListener { editable ->
            if (editable.toString().isNotEmpty()) {
                viewModel.searchNews(editable.toString()).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            result.data?.let {
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
        }
    }

    private fun showMessage(message: String?) {
        requireContext().toast(message)
    }

    private fun showLoading(visible: Boolean) {
        binding.paginationProgressBar.isVisible = visible
    }

    private fun navigateToArticle() {
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putParcelable(ARTICLE, it)
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}