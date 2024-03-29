package com.route.newsappc39_g_sun_wed

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc39_g_sun_wed.ui.theme.NewsAppC39GSunWedTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC39GSunWedTheme {
                // A surface container using the 'background' color from the theme
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@SplashActivity, NewsActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2500)
                SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {

    //|  Hello         Spacer (filled = true)    World|
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds //scaleType="fitXY"
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo_image),
            modifier = Modifier.fillMaxHeight(0.3F),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = stringResource(
                R.string.app_development_signature

            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.4F)
        )
//        Spacer(modifier = Modifier.weight(1F))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashContentPreview() {
    SplashContent()
}

