package com.route.newsappc39_g_sun_wed.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.route.newsappc39_g_sun_wed.api.APIManager
import com.route.newsappc39_g_sun_wed.model.Constants
import com.route.newsappc39_g_sun_wed.model.api.ArticlesItem
import com.route.newsappc39_g_sun_wed.model.api.ArticlesResponse
import com.route.newsappc39_g_sun_wed.utils.NewsCard
import com.route.newsappc39_g_sun_wed.utils.NewsSourcesTabRow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsFragment(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    category: String
) {
    val newsListStates = remember {
        mutableStateListOf<ArticlesItem>()
    }

    Column(modifier = modifier) {
        NewsSourcesTabRow(category = category) { sourceId ->
            APIManager
                .getNewsServices()
                .getNewsBySource(Constants.API_KEY, sourceId)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                    ) {
                        newsListStates.clear()
                        val newsList = response.body()?.articles
                        if (newsList?.isNotEmpty() == true) {
                            newsListStates.addAll(newsList)
                        }
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })

        }
        NewsList(newsListStates.toList())
    }
}

@Composable
fun NewsList(newsList: List<ArticlesItem>) {
    LazyColumn {
        items(newsList.size) { position ->
            NewsCard(model = newsList[position])
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsCardPreview() {
}