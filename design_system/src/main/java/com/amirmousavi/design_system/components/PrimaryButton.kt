package com.amirmousavi.design_system.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.amirmousavi.design_system.LocalSpacing

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    startIcon: @Composable () -> Unit = {
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.size(spacing.iconSize))
    },
    endIcon: @Composable () -> Unit = {
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.size(spacing.iconSize))
    },
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit = {},
) {

    val spacing = LocalSpacing.current
    Button(
        onClick = {
            if (!loading) {
                onClick()
            }
        },
        shape = RoundedCornerShape(spacing.spaceSmall),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = spacing.spaceSmall,
            vertical = spacing.spaceExtraSmall
        ),
        enabled = enabled,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                spacing.spaceSmall, Alignment.Start
            )
        ) {
            startIcon()

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            endIcon()
        }
    }

}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "ورود",
        startIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = "") },
        onClick = {}

    )
}