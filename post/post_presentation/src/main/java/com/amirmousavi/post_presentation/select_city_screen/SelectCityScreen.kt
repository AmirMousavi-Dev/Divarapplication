package com.amirmousavi.post_presentation.select_city_screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.hilt.navigation.compose.hiltViewModel
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.R
import com.amirmousavi.design_system.UiEvent
import com.amirmousavi.design_system.components.LocationPermissionTextProvider
import com.amirmousavi.design_system.components.PermissionDialog
import com.amirmousavi.design_system.components.PrimaryButton
import com.amirmousavi.post_presentation.select_city_screen.component.CityItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@Composable
fun SelectCityScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectCityViewModel = hiltViewModel(),
    onNavigate :() -> Unit
) {
    val state by viewModel.state.collectAsState()
    val uiEvent = viewModel.uiEvent

    val spacing = LocalSpacing.current
    val activity = LocalContext.current.findActivity()
    var shouldShowSuccessDialog by remember {
        mutableStateOf(false)
    }

    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)


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

            if(isGranted) {
                getCurrentLocation(
                    fusedLocationProviderClient = fusedLocationProviderClient,
                    activity = activity,
                    onGetCurrentLocationSuccess = {latitude,longitude->
                        viewModel.onEvent(
                            SelectCityContract.Event.SaveCityByLocation(
                                latitude = latitude ,
                                longitude = longitude
                            )
                        )

                    },
                    onGetCurrentLocationFailed = {
                        Toast.makeText(activity.baseContext, it.message, Toast.LENGTH_SHORT).show()
                    }
                )
            }
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


    if (shouldShowSuccessDialog) {
        AlertDialog(
            onDismissRequest = {
                shouldShowSuccessDialog = false
                onNavigate()
            },
            title = {
                Text(stringResource(R.string.select_city))
            },
            text = {
                Text(stringResource(R.string.your_city_successfully_changed))
            },
            confirmButton = {
                PrimaryButton(
                    text = stringResource(R.string.ok),
                    modifier= Modifier.fillMaxWidth()
                        .padding(spacing.spaceMedium),
                    onClick = {
                        shouldShowSuccessDialog = false
                        onNavigate()
                    }

                )
            }
        )
    }


    LaunchedEffect(uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> shouldShowSuccessDialog = true
                is UiEvent.ShowMessage -> {
                    Toast.makeText(activity.applicationContext, event.message.asString(activity.applicationContext), Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

        SelectCityContent(
            state = state,
            modifier = modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            onSelectCityClick = {

                getCurrentLocation(
                    fusedLocationProviderClient = fusedLocationProviderClient,
                    activity = activity,
                    onGetCurrentLocationSuccess = {latitude,longitude->
                        viewModel.onEvent(
                            SelectCityContract.Event.SaveCityByLocation(
                                latitude = latitude ,
                                longitude = longitude
                            )
                        )

                    },
                    onGetCurrentLocationFailed = {
                        Toast.makeText(activity.baseContext, it.message, Toast.LENGTH_SHORT).show()
                    },
                    requestPermission = {
                        locationPermissionResultLauncher.launch(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    }
                )

            },
            onCityClick = {city->
                viewModel.onEvent(SelectCityContract.Event.OnCitySelected(
                    cityId = city.id,
                    cityFaName = city.name
                ))
            }
        )



}

@Composable
fun SelectCityContent(
    state: SelectCityContract.State,
    modifier: Modifier = Modifier,
    onSelectCityClick :() -> Unit,
    onCityClick :(CityEntity) -> Unit
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
                {onCityClick(city)}
            }
        }
    }


}



@SuppressLint("MissingPermission")
private fun getCurrentLocation(
    fusedLocationProviderClient: FusedLocationProviderClient,
    activity: Activity,
    onGetCurrentLocationSuccess: (latitude :Double,longitude: Double) -> Unit,
    onGetCurrentLocationFailed: (Exception) -> Unit,
    requestPermission: () ->Unit = {},
) {
    if (areLocationPermissionsGranted(activity)) {
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token,
        ).addOnSuccessListener { location ->
            location?.let {
                onGetCurrentLocationSuccess(it.latitude, it.longitude)
            }
        }.addOnFailureListener { exception ->
            onGetCurrentLocationFailed(exception)
            exception.printStackTrace()
        }
    } else {
        requestPermission()
    }
}

private fun areLocationPermissionsGranted(
    activity: Activity
): Boolean {
    return (ActivityCompat.checkSelfPermission(
        activity.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                activity.applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
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
        ),
        onSelectCityClick = {},
        onCityClick = {}
    )
}