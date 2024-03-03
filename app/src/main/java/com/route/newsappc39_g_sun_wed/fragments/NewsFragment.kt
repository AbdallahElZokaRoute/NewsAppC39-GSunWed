package com.route.newsappc39_g_sun_wed.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.route.newsappc39_g_sun_wed.api.APIManager
import com.route.newsappc39_g_sun_wed.model.Constants
import com.route.newsappc39_g_sun_wed.model.api.ArticlesItem
import com.route.newsappc39_g_sun_wed.model.api.ArticlesResponse
import com.route.newsappc39_g_sun_wed.ui.news.NewsViewModel
import com.route.newsappc39_g_sun_wed.utils.NewsCard
import com.route.newsappc39_g_sun_wed.utils.NewsSourcesTabRow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsappc39_g_sun_wed.ui.theme.green

// Categories Fragment
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFragment(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    category: String,
    viewModel: NewsViewModel = viewModel()
) {
    if (viewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = green)
        }
    }
    Column(modifier = modifier) {
        NewsSourcesTabRow(category = category) { sourceId ->
            viewModel.getNewsBySource(sourceId)
        }
        NewsList(viewModel.newsListStates.toList())
    }
    if (viewModel.messageState.value.isNotEmpty())
        AlertDialog(
            onDismissRequest = { viewModel.messageState.value = "" },
            confirmButton = {
                TextButton(onClick = { viewModel.messageState.value = "" }) {
                    Text(text = "OK")
                }
            }, title = {
                Text(text = viewModel.messageState.value)
            }
        )

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