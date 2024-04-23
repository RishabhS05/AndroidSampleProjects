package com.example.imageloadingandinfinitescrolling.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baseArchitecture.presentation.BaseActivity
import com.example.imageloadingandinfinitescrolling.presentation.ui.theme.ImageLoadingAndinfiniteScrollingTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : BaseActivity() {
    val baseUrl = "https://picsum.photos/v2/list?page=2&limit=100"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageLoadingAndinfiniteScrollingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageLoadingAndinfiniteScrollingTheme {
        Greeting("Android")
    }
}