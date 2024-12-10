package com.amirmousavi.post_presentation.select_city_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.design_system.LocalSpacing

@Composable
fun CityItem(
    city: CityEntity,
    modifier: Modifier = Modifier,
    onCityClick: () -> Unit
) {

    val spacing = LocalSpacing.current
    Card(
        modifier = modifier,
        onClick = onCityClick,
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onSurface),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = city.name,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge
            )


            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = city.name
            )
        }

    }


}
