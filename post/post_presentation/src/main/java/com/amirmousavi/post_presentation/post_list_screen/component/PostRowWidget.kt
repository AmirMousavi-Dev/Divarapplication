package com.amirmousavi.post_presentation.post_list_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.R
import com.amirmousavi.post_presentation.model.PostRowUiModel
import com.amirmousavi.post_presentation.model.WidgetUiModel
import com.amirmousavi.post_presentation.util.Widget
import com.amirmousavi.post_presentation.util.asPostRowUiModel

class PostRowWidget(
) : Widget {

    @Composable
    override fun Render(widgetUiModel: WidgetUiModel, modifier: Modifier, onClick: () -> Unit) {
        val spacing = LocalSpacing.current

        ElevatedCard(
            modifier = modifier,
            onClick = onClick,
            colors = CardDefaults
                .elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
        ) {
            PostRow(
                widgetUiModel.asPostRowUiModel(), modifier = Modifier
                    .fillMaxWidth()
                    .height(152.dp)
                    .padding(spacing.spaceSmall)
            )
        }

    }
}


@Composable
private fun PostRow(
    postRowUiModel: PostRowUiModel,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            postRowUiModel.title?.let { title ->
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart),
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),

                ) {

                postRowUiModel.price?.let { price ->
                    Text(
                        text = price,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                postRowUiModel.district?.let { district ->
                    Text(
                        text = district,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

            }
        }



        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(postRowUiModel.thumbnail)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_image),
            error = painterResource(R.drawable.ic_error),
            contentDescription = postRowUiModel.title,

            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(152.dp)
                .clip(RoundedCornerShape(spacing.spaceSmall)),
        )

    }

}

@Preview
@Composable
private fun PostRowPreview() {
    PostRow(
        PostRowUiModel(
            title = "مبل کلاسیک شیک چستر دکمه خور",
            token = "QYLy9X7q",
            price = "۶,۵۰۰,۰۰۰ تومان",
            district = "مبلمان در شهرک ولیعصر",
            thumbnail = "https://amirmohammadi.ir/wp-content/uploads/2024/03/9223372036854775808_-210453.jpg",
        ), modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(16.dp)
    )
}