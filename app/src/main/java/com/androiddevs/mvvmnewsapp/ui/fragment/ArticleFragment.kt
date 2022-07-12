package com.androiddevs.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentArticleBinding
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel
import com.androiddevs.mvvmnewsapp.utils.Extension.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)

        setupWebView()
        savedNews()
    }

    private fun setupWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.article.url)
        }
    }

    private fun savedNews() {
        binding.fab.setOnClickListener {
            viewModel.upsert(args.article)
            "Article saved successfully".snackbar(requireView())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}