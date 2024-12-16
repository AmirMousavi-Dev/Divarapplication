package com.amirmousavi.post_presentation.post_list_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.post_presentation.util.Widget

class SubTitleRowWidget(
) : Widget {


    @Composable
    override fun Render(postEntity: PostEntity, modifier: Modifier, onClick: () -> Unit) {
        postEntity.text?.let {
            SubTitleRow(it, modifier)
        }
    }
}


@Composable
private fun SubTitleRow(
    text: String,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Start,
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun SubTitleRowPreview() {
    SubTitleRow(
        text = "آخرین آگهی های موجود در شهر تهران"
    )
}