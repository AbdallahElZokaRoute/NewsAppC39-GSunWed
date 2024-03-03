package com.route.newsappc39_g_sun_wed.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsappc39_g_sun_wed.R
import com.route.newsappc39_g_sun_wed.model.api.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(model: ArticlesItem) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        GlideImage(
            model = model.urlToImage ?: "",
            contentDescription = stringResource(R.string.news_image),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
            loading = placeholder(R.drawable.logo),
        )
        Text(
            text = model.source?.name ?: "",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 10.sp
        )
        Text(
            text = model.title ?: "",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp, fontWeight = FontWeight.Medium
        )
        Text(
            text = model.publishedAt ?: "",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.End),
            fontSize = 13.sp
        )
        // News App ->   90%  -> Retrofit  -> Graduation Project -> E-Commerce Project
        //            2 Months <-> 
        // MVVM UI Architecture Pattern + Data Binding + Binding Adapters
        //               Phone OTP
        // Chat App (Firebase -> Firestore - Authentication - Cloud Messaging)
        // Telegram Group Chat
        // Coroutines
        // Repository Pattern
        // Dependency Injection using Dagger Hilt
        // MVI UI Architecture pattern
        // Unit Testing + UI Testing
        // Clean Code Architecture
    }
}