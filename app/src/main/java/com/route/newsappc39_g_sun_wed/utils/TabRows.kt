package com.route.newsappc39_g_sun_wed.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.newsappc39_g_sun_wed.api.APIManager
import com.route.newsappc39_g_sun_wed.model.Category
import com.route.newsappc39_g_sun_wed.model.Constants
import com.route.newsappc39_g_sun_wed.model.api.SourcesItem
import com.route.newsappc39_g_sun_wed.model.api.SourcesResponse
import com.route.newsappc39_g_sun_wed.ui.news.NewsViewModel
import com.route.newsappc39_g_sun_wed.ui.theme.green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewsSourcesTabRow(
    category: String,
    viewModel: NewsViewModel = viewModel(),
    onTabSelected: (sourceId: String) -> Unit
) {

    //
    LaunchedEffect(Unit) {
        viewModel.getNewsSources(category)
    }
    if (viewModel.sourcesList.isNotEmpty()) {
        LaunchedEffect(Unit) {
            val sourceId = viewModel.sourcesList.get(0).id
            onTabSelected(sourceId ?: "")
        }
    }
    ScrollableTabRow(
        selectedTabIndex = viewModel.selectedTabIndex.intValue,
        edgePadding = 8.dp,
        indicator = {},
        divider = {}) {
        viewModel.sourcesList.forEachIndexed { index, item ->
            Tab(
                selected = index == viewModel.selectedTabIndex.intValue,
                onClick = {
                    onTabSelected(item.id ?: "")
                    viewModel.selectedTabIndex.intValue = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = green
            ) {
                Text(
                    text = item.name ?: "",
                    modifier = if (viewModel.selectedTabIndex.intValue == index)
                        Modifier
                            .padding(8.dp)
                            .background(green, RoundedCornerShape(50))
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    else
                        Modifier
                            .padding(8.dp)
                            .border(2.dp, green, CircleShape)
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}