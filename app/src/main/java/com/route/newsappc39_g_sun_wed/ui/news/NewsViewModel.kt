package com.route.newsappc39_g_sun_wed.ui.news

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.route.newsappc39_g_sun_wed.api.APIManager
import com.route.newsappc39_g_sun_wed.model.Constants
import com.route.newsappc39_g_sun_wed.model.api.ArticlesItem
import com.route.newsappc39_g_sun_wed.model.api.ArticlesResponse
import com.route.newsappc39_g_sun_wed.model.api.SourcesItem
import com.route.newsappc39_g_sun_wed.model.api.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// View Model -> LOGIC & States
class NewsViewModel : ViewModel() {
    // Data
    val newsListStates = mutableStateListOf<ArticlesItem>()
    val selectedTabIndex = mutableIntStateOf(0)
    val sourcesList = mutableStateListOf<SourcesItem>()
    val isLoading = mutableStateOf(false)
    val messageState = mutableStateOf("")
    fun getNewsBySource(sourceId: String) {
        isLoading.value = true
        APIManager
            .getNewsServices()
            .getNewsBySource(Constants.API_KEY, sourceId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    isLoading.value = false
                    newsListStates.clear()
                    val newsList = response.body()?.articles
                    if (newsList?.isNotEmpty() == true) {
                        newsListStates.addAll(newsList)
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    isLoading.value = false
                    messageState.value = "${t.message}"
                }


            })
    }

    fun getNewsSources(category: String) {
        isLoading.value = true
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, category)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    isLoading.value = false
                    val sources = response.body()?.sources
                    if (sources?.isNotEmpty() == true) {
                        sourcesList.addAll(sources)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    isLoading.value = false
                    messageState.value = "${t.message}"
                }


            }) // Background Thread
//            .execute() // Main Thread (UI Thread)   -> User-> User Clicks , and navigation
    }

}