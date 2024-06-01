package com.example.imageloadingandinfinitescrolling.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.baseArchitecture.presentation.ui.base.BaseActivity
import com.example.baseArchitecture.presentation.ui.widget.LoadImage
import com.example.baseArchitecture.uitls.OnBottomReached
import com.example.imageloadingandinfinitescrolling.BuildConfig.BASE_URL
import com.example.imageloadingandinfinitescrolling.presentation.ui.theme.ImageLoadingAndinfiniteScrollingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val  viewModel : MainViewModel  by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImageList()
        setContent {
            ImageLoadingAndinfiniteScrollingTheme {
              listImages()}
            }
        }
    @Composable
    fun listImages() {
        val state = viewModel.uiState.collectAsState()
        val listState = rememberLazyListState()
        listState.OnBottomReached {
         viewModel.getImageList()
        }
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(
                count = state.value.data.size
            ){ index->
                //do not do this as my api will not change so  need to edit here
                val item =  state.value.data[index]
                Text(text = "id : ${item.id} ${item.author}")
                LoadImage(url = "$BASE_URL/id/${item.id}/1080/720", imageId = item.id,){}
            }
        }
    }
}


