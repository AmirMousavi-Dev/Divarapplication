package com.amirmousavi.post_presentation.post_detail_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.post_presentation.model.WidgetUiModel
import com.amirmousavi.post_presentation.util.Widget


class DescriptionRowWidget(
) : Widget {

    @Composable
    override fun Render(widgetUiModel: WidgetUiModel, modifier: Modifier, onClick: () -> Unit) {

        widgetUiModel.text?.let {
            DescriptionRow(
                description = widgetUiModel.text,
                modifier = modifier
            )
        }
    }
}


@Composable
fun DescriptionRow(
    description: String,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        HorizontalDivider()
    }


}


@Preview
@Composable
private fun DescriptionRowPreview() {
    DescriptionRow(
        description = "مودم با طرح یکساله400گیگ\nجشنواره مبین نت با با قیمت باورنکردنی\nسرعت 4الی40مگ\nبدون نیاز به خط تلفن\nقابلیت گرفتن ip استاتیک\nقابل حمل بدون نیاز به خط تلفن\nبا طرح های \nسه ماهه\nشش ماهه\nیکساله\nارسال به سراسر کشور",
        modifier = Modifier.fillMaxWidth()
    )
}