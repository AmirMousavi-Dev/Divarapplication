package com.amirmousavi.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


abstract class BaseApiResponse {
    suspend fun <D> safeApiCall(
        apiCall: suspend () -> D,
    ): Result<D,DataError> =
        withContext(Dispatchers.IO) {
            try {
               return@withContext Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                 when(throwable){
                    is IOException -> {
                        return@withContext Result.Error(DataError.Network.NetworkDisabled)
                    }
                     is HttpException -> {
                         if (throwable.code() == 408)
                         return@withContext Result.Error(DataError.Network.RequestTimeOut)
                         else if (throwable.code() == 401)
                             return@withContext Result.Error(DataError.Network.AuthenticationError)
                         else
                         return@withContext Result.Error(DataError.Network.Unknown)
                     }
                     else -> {
                         return@withContext Result.Error(DataError.Network.Unknown)
                     }
                }
            }
        }

}