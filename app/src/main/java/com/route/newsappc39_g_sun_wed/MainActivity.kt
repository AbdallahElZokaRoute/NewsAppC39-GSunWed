package com.route.newsappc39_g_sun_wed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc39_g_sun_wed.ui.theme.NewsAppC39GSunWedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC39GSunWedTheme {
                // A surface container using the 'background' color from the theme
                CounterScreenContent()
            }
        }
    }
}

data class SettingsItem(val id: String)

// Counter Screen
@Composable
fun CounterScreenContent() {
    // 0
    val counter = remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You Pressed ${counter.intValue} of times")
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {
            counter.intValue += 1  // counter = 0 +  1 = 1
        }) {
            Text(text = "Increment")
        }
        Button(onClick = {
            counter.intValue -= 1  // counter = 0 +  1 = 1
        }) {
            Text(text = "Decrement")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterPreview() {
    CounterScreenContent()
}
