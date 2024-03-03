package com.route.newsappc39_g_sun_wed.model

import com.route.newsappc39_g_sun_wed.R

// Inheritance -> final
open class Screen(val route: String, val description: Int)

class NewsScreen : Screen("news", R.string.news_app) {
    companion object {
        val ROUTE_NAME = "news"
    }
}

class CategoriesScreen : Screen("categories", R.string.categories) {
    companion object {
        val ROUTE_NAME = "categories"
    }

}

// Login -> login
// Register

