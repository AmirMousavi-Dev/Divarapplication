package com.amirmousavi.post_presentation.post_list_screen.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.amirmousavi.post_presentation.model.WidgetUiModel
import com.amirmousavi.post_presentation.util.Widget

class TitleRowWidget(
) : Widget {


    @Composable
    override fun Render(
        widgetUiModel: WidgetUiModel, modifier: Modifier, onClick: () -> Unit
    ) {
        widgetUiModel.text?.let {
            TitleRow(it, modifier)

        }
    }

}

@Composable
private fun TitleRow(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Start,
        modifier = modifier
    )
}


@Preview
@Composable
private fun TitleRowPreview() {
    TitleRow(
        text = "همهٔ آگهی ها"
    )
}