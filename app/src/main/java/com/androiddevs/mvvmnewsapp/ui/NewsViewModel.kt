package com.androiddevs.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.data.remote.response.Article
import com.androiddevs.mvvmnewsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getBreakingNews() = newsRepository.getBreakingNews()

    fun searchNews(searchQuery: String) = newsRepository.searchNews(searchQuery)

    fun upsert(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedArticle() = newsRepository.getSavedArticle()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}