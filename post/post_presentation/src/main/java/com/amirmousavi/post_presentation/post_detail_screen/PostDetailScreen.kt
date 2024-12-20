package com.amirmousavi.post_presentation.post_detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.components.PrimaryButton

@Composable
fun PostDetailScreen(
    token: String,
    modifier: Modifier = Modifier,
    viewModel: PostDetailViewModel = hiltViewModel(),
) {

    val spacing = LocalSpacing.current

    val contactButtonState by viewModel.contactButtonState.collectAsState()
    val widgetState by viewModel.widgetState.collectAsState()

    LaunchedEffect(token) {
        viewModel.getPostDetail(token)
    }

    Column(
        modifier = modifier
            .padding(vertical = spacing.spaceSmall)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            items(widgetState) { item ->
                item.second.Render(
                    widgetUiModel = item.first,
                    modifier = Modifier
                        .padding(horizontal = spacing.spaceSmall), onClick = {

                    }
                )
            }
        }


        contactButtonState?.let { contactButton ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceSmall)
            ) {
                PrimaryButton(
                    text = contactButton.text,
                    enabled = contactButton.enable

                )
            }
        }
    }


}