package com.amirmousavi.post_presentation.post_detail_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.R
import com.amirmousavi.post_presentation.model.ImageSliderUiModel
import com.amirmousavi.post_presentation.model.WidgetUiModel
import com.amirmousavi.post_presentation.util.Widget
import com.amirmousavi.post_presentation.util.asImageSliderUiModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


class ImageSliderRowWidget(
) : Widget {

    @Composable
    override fun Render(widgetUiModel: WidgetUiModel, modifier: Modifier, onClick: () -> Unit) {


        ImageSliderRow(
            imageList = widgetUiModel.items?.map { it.asImageSliderUiModel() } ?: emptyList(),
            modifier = modifier
        )
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSliderRow(
    imageList: List<ImageSliderUiModel>,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

    val pagerState = rememberPagerState(
        pageCount = imageList.size,
    )


    Box {

        HorizontalPager(
            state = pagerState,
        ) { index ->

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageList[index].url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_image),
                    error = painterResource(R.drawable.ic_error),
                    contentDescription = imageList[index].alt,

                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(256.dp),
                )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(spacing.spaceSmall)
        )

    }



}