package com.route.newsappc39_g_sun_wed.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc39_g_sun_wed.R
import com.route.newsappc39_g_sun_wed.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(titleResId: Int, onSideMenuClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(R.string.navigation_drawer_icon),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onSideMenuClick()
                    }
            )
        },
        title = {
            Text(
                text = stringResource(titleResId), modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = green,
            titleContentColor = Color.White
        ),
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 30.dp,
                bottomEnd = 30.dp
            )
        ), actions = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(
                    R.string.icon_search
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}

@Preview
@Composable
fun NewsTopAppBarPreveiw() {
    NewsTopAppBar(R.string.news_app) {

    }
}
