package com.androiddevs.mvvmnewsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.androiddevs.mvvmnewsapp.data.local.room.NewsDao
import com.androiddevs.mvvmnewsapp.data.remote.NewsApi
import com.androiddevs.mvvmnewsapp.data.remote.response.NewsResponse
import com.androiddevs.mvvmnewsapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val dao: NewsDao,
    private val api: NewsApi
) {
    fun getBreakingNews(): LiveData<Resource<NewsResponse>> = liveData {
        try {
            emit(Resource.Loading())
            val data = api.getBreakingNews()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage!!))
        } catch (e: IOException) {
            emit(Resource.Error("No Internet Connection"))
        }
    }
}