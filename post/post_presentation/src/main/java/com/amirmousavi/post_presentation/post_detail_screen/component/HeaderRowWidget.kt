package com.amirmousavi.post_presentation.post_detail_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.post_presentation.model.HeaderRowUiModel
import com.amirmousavi.post_presentation.model.WidgetUiModel
import com.amirmousavi.post_presentation.util.Widget
import com.amirmousavi.post_presentation.util.asHeaderRowUiModel

class HeaderRowWidget(
) : Widget {

    @Composable
    override fun Render(widgetUiModel: WidgetUiModel, modifier: Modifier, onClick: () -> Unit) {
        HeaderRow(
            headerRowUiModel = widgetUiModel.asHeaderRowUiModel(),
            modifier = modifier
        )
    }

}

@Composable
fun HeaderRow(
    headerRowUiModel: HeaderRowUiModel,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
    ) {
        Text(
            text = headerRowUiModel.title,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = headerRowUiModel.subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )

        HorizontalDivider()
    }
}


@Preview
@Composable
private fun HeaderRowPreview() {
    HeaderRow(
        headerRowUiModel = HeaderRowUiModel(
            title = "مودم مبین نت با طرح 360 طرح یکساله",
            subtitle = "لحظاتی پیش در تهران، سعادت‌آباد",
            imageUrl = null,
            showThumbnail = false
        )
    )
}