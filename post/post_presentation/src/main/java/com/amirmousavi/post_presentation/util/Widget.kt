package com.amirmousavi.post_presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amirmousavi.core.domain.model.PostEntity

interface Widget {

    @Composable
    fun Render(postEntity: PostEntity, modifier: Modifier, onClick: () -> Unit = {})
}