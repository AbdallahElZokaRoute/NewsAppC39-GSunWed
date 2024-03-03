package com.route.newsappc39_g_sun_wed.ui.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsappc39_g_sun_wed.R
import com.route.newsappc39_g_sun_wed.fragments.CategoriesFragment
import com.route.newsappc39_g_sun_wed.fragments.NewsFragment
import com.route.newsappc39_g_sun_wed.model.CategoriesScreen
import com.route.newsappc39_g_sun_wed.model.NewsScreen
import com.route.newsappc39_g_sun_wed.ui.theme.NewsAppC39GSunWedTheme
import com.route.newsappc39_g_sun_wed.utils.NavigationDrawerSheet
import com.route.newsappc39_g_sun_wed.utils.NewsTopAppBar
import kotlinx.coroutines.launch

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC39GSunWedTheme {
                // A surface container using the 'background' color from the theme
                NewsScreenContent()
            }
        }
    }
}

// 1- Refactor -> News Activity

@Composable
fun NewsScreenContent() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val toolbarTitle = remember {
        mutableIntStateOf(R.string.news_app)
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Drawer Sheet  // Side Menu
            NavigationDrawerSheet(onSettingsClick = {
                scope.launch {
                    drawerState.close()
                }
            }, onCategoriesClick = {
                navController.popBackStack()
                if (navController.currentDestination?.route != CategoriesScreen().route) {
                    navController.navigate(CategoriesScreen().route)
                }
                scope.launch {
                    drawerState.close()
                }
            })
        }) {
        Scaffold(
            topBar = {
                // Top App Bar
                NewsTopAppBar(titleResId = toolbarTitle.intValue) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }, modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->

            // FrameLayout -> Categories Fragment -> News Fragment  
            // enum classes & sealed class
            NavHost(
                navController = navController,
                startDestination = CategoriesScreen().route,
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
            ) {
                //              "categories"
                composable(CategoriesScreen.ROUTE_NAME) {
                    toolbarTitle.intValue = R.string.news_app
                    CategoriesFragment(navController)
                }
                composable(
                    "${NewsScreen.ROUTE_NAME}/{category_name}/{category_id}",
                    arguments = listOf(navArgument("category_id") {
                        type = NavType.StringType
                    }, navArgument("category_name") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val categoryId = navBackStackEntry.arguments?.getString("category_id")
                    val categoryName =
                        navBackStackEntry.arguments?.getInt("category_name")
                    toolbarTitle.intValue = categoryName ?: R.string.news_app
                    NewsFragment(category = categoryId ?: "", navHostController = navController)
                }

            }
            // news -> 
            // categories -> 
            // 2 options ->
            // 1- Handle It by ourselves

            // NavHost -> FrameLayout -> Fragment Container
            // NavController -> Navigate from screen to another screen
        }
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun NewsListPreview() {
//    NewsList()
//}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFragmentPreview() {
    NewsFragment(category = "", navHostController = rememberNavController())
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsScreenPreview() {
    NewsScreenContent()
}

// Mobile ->   Retrofit ->                       -> News API .org
//             Retrofit Converters ->

// JSON -> Kotlin  -> Deserialization
// Kotlin -> JSON  -> Serialization
