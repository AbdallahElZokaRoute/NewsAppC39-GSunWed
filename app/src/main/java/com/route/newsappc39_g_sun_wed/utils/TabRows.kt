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
import com.route.newsappc39_g_sun_wed.ui.theme.green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsSourcesTabRow(category: String, onTabSelected: (sourceId: String) -> Unit) {
    val selectedTabIndex = remember {
        mutableIntStateOf(0)
    }
    val sourcesList = remember {
        mutableStateListOf<SourcesItem>()
    }
    //
    LaunchedEffect(Unit) {
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, category)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val sources = response.body()?.sources
                    if (sources?.isNotEmpty() == true) {
                        sourcesList.addAll(sources)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            }) // Background Thread
//            .execute() // Main Thread (UI Thread)   -> User-> User Clicks , and navigation
    }
    if (sourcesList.isNotEmpty()) {
        LaunchedEffect(Unit) {
            val sourceId = sourcesList.get(0).id
            onTabSelected(sourceId ?: "")
        }
    }
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex.intValue,
        edgePadding = 8.dp,
        indicator = {},
        divider = {}) {
        sourcesList.forEachIndexed { index, item ->
            Tab(
                selected = index == selectedTabIndex.intValue,
                onClick = {
                    onTabSelected(item.id ?: "")
                    selectedTabIndex.intValue = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = green
            ) {
                Text(
                    text = item.name ?: "", modifier = if (selectedTabIndex.intValue == index)
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