package com.amirmousavi.design_system.util

import com.amirmousavi.core.util.DataError
import com.amirmousavi.design_system.R
import com.amirmousavi.design_system.UiText

fun DataError.asUiText() :UiText {
    return when(this) {
        DataError.Network.AuthenticationError -> UiText.StringResource(R.string.authentication_error)
        DataError.Network.NetworkDisabled -> UiText.StringResource(R.string.network_disabled)
        DataError.Network.RequestTimeOut -> UiText.StringResource(R.string.request_timeout)
        DataError.Network.Unknown -> UiText.StringResource(R.string.unknown_error)
    }
}
