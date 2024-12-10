package com.amirmousavi.post_presentation.select_city_screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.hilt.navigation.compose.hiltViewModel
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.components.LocationPermissionTextProvider
import com.amirmousavi.design_system.components.PermissionDialog
import com.amirmousavi.design_system.components.PrimaryButton
import com.amirmousavi.design_system.R
import com.amirmousavi.post_presentation.select_city_screen.component.CityItem

@Composable
fun SelectCityScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectCityViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val spacing = LocalSpacing.current

    val activity = LocalContext.current.findActivity()

    LaunchedEffect(true) {
        viewModel.onEvent(SelectCityContract.Event.GetCities)
    }

    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onEvent(
                SelectCityContract.Event.OnPermissionResult(
                    isGranted ,
                    Manifest.permission.ACCESS_FINE_LOCATION)
            )
        }
    )


    state.permissionDialogQueue
        .reversed()
        .forEach {permission ->
            PermissionDialog(
                permissionTextProvider = LocationPermissionTextProvider(),
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                    activity,
                    permission
                ),
                onDismiss = {
                    viewModel.onEvent(
                        SelectCityContract.Event.OnPermissionDialogDismiss
                    )},
                onOkClick = {
                    viewModel.onEvent(
                        SelectCityContract.Event.OnPermissionDialogDismiss
                    )
                    locationPermissionResultLauncher.launch(permission)
                },
                onGoToAppSettingClick = {
                    activity.openAppSettings()
                }
            )
        }



        SelectCityContent(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            onSelectCityClick = {
                locationPermissionResultLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        )



}

@Composable
fun SelectCityContent(
    state: SelectCityContract.State,
    modifier: Modifier = Modifier,
    onSelectCityClick :() -> Unit
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        PrimaryButton(
            text = stringResource(R.string.select_city_by_location),
            modifier = Modifier.fillMaxWidth(),
            endIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null
                )
            },
            onClick = {
                onSelectCityClick()
            }
        )
        LazyColumn(modifier = modifier.weight(1f) ,
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            items(state.cities, key = { it.id }) { city ->
                CityItem(
                    city = city,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                {}
            }
        }
    }


}


fun android.content.Context.findActivity(): androidx.activity.ComponentActivity {
    var context = this
    while (context is android.content.ContextWrapper) {
        if (context is androidx.activity.ComponentActivity) {
            return context
        }
        context = context.baseContext
    }
    throw IllegalStateException("No Activity found!")
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName,null)
    ).also(::startActivity)
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
    ) {}
}