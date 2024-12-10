package com.amirmousavi.design_system.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.amirmousavi.design_system.LocalSpacing
import com.amirmousavi.design_system.R
import com.amirmousavi.design_system.UiText

@Composable
fun PermissionDialog(
    permissionTextProvider : PermissionTextProvider,
    isPermanentlyDeclined :Boolean,
    onDismiss :() ->Unit,
    onOkClick :() ->Unit,
    onGoToAppSettingClick :() ->Unit,
    modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {


                PrimaryButton(
                    text =  if (isPermanentlyDeclined) stringResource(R.string.grant_permission) else stringResource(R.string.ok),
                    modifier = Modifier.fillMaxWidth()
                        .padding(spacing.spaceMedium),
                    onClick = {
                        if (isPermanentlyDeclined)
                            onGoToAppSettingClick()
                        else
                            onOkClick()
                    }
                )

        },
        title = {
            Text(
                text = permissionTextProvider.title.asString()
            )
        },
        text = {
            Text(
                text = permissionTextProvider.description(isPermanentlyDeclined).asString()
            )
        },
        modifier = modifier
    )

}

interface PermissionTextProvider{
    val title :UiText
    fun description(isPermanentlyDeclined: Boolean) :UiText
}

class LocationPermissionTextProvider :PermissionTextProvider {
    override val title: UiText
        get() = UiText.StringResource(R.string.location_permission_title)

    override fun description(isPermanentlyDeclined: Boolean): UiText {
        return if (isPermanentlyDeclined)
            UiText.StringResource(R.string.location_permission_permanently_declined)
        else
            UiText.StringResource(R.string.location_permission_rationale)
    }
}