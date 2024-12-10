package com.amirmousavi.post_presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCityScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectCityViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val searchQuery by viewModel.searchQuery.collectAsState()

    LaunchedEffect(true) {
        viewModel.onEvent(SelectCityContract.Event.GetCities)
    }



    Column {

        SelectCityContent(
            state = state,
            modifier = Modifier.fillMaxWidth()
        )
    }


}

@Composable
fun SelectCityContent(
    state: SelectCityContract.State,
    modifier: Modifier = Modifier
) {


    LazyColumn(modifier = modifier) {
        items(state.cities, key = { it.id }) { city ->
            CityItem(
                city = city,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            {}
        }
    }


}

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

@Preview
@Composable
private fun SelectCityScreenPreview() {

    SelectCityContent(
        state = SelectCityContract.State(
            cities = listOf(
                CityEntity(1, "Tehran"),
                CityEntity(2, "Karaj"),
                CityEntity(3, "Mashhad"),
                CityEntity(4, "Tabriz"),
            )
        )
    )
}