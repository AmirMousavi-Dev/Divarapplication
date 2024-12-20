package com.amirmousavi.core.util


sealed interface DataError :Error {
    sealed class Network :DataError {
        data object RequestTimeOut :DataError
        data object AuthenticationError :DataError
        data object NetworkDisabled :DataError
        data object Unknown :DataError
    }
}

