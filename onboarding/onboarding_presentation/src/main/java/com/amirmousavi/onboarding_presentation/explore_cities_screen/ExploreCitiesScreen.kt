package com.amirmousavi.onboarding_presentation.explore_cities_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.R
import com.amirmousavi.design_system.components.PrimaryButton

@Composable
fun ExploreCitiesScreen(
    modifier: Modifier = Modifier,
    onSelectCityClick: () -> Unit
) {

    val spacing = LocalSpacing.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.ic_explore_cities),
            contentDescription = stringResource(R.string.explore_cities_screen_image)
        )


        Text(
            text = stringResource(R.string.explore_city_title),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.explore_city_message),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(spacing.spaceLarge))

        PrimaryButton(
            text = stringResource(R.string.select_city),
            modifier = Modifier.fillMaxWidth(),
            onClick = onSelectCityClick
        )


    }
}


@Preview
@Composable
private fun ExploreCitiesScreenPreview() {


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {


        ExploreCitiesScreen(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

        }
    }
}