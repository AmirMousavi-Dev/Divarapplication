package com.amirmousavi.post_presentation.post_list_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.amirmousavi.design_system.LocalSpacing
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostListViewModel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        viewModel.getPosts()
    }

    val state = viewModel.postsList.collectAsLazyPagingItems()
    val context = LocalContext.current
    val spacing = LocalSpacing.current

    LaunchedEffect(state.loadState) {
        if (state.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (state.loadState.refresh as LoadState.Error).error.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        if (state.loadState.append is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (state.loadState.append as LoadState.Error).error.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    val pullToRefreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spacing.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {

            items(state) { item ->
                item?.let {
                    item.second.Render(
                        postEntity = item.first,
                        modifier = Modifier, onClick = {
                            Toast.makeText(context, it.first.token, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

            item {
                if (state.loadState.append is LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.spaceSmall),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

        }



        if (pullToRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                pullToRefreshState.startRefresh()
                viewModel.getPosts()
                delay(1000)
                pullToRefreshState.endRefresh()
            }
        }


        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}