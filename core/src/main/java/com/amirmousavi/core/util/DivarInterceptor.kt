package com.amirmousavi.core.util

import okhttp3.Interceptor
import okhttp3.Response

class DivarInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader(
                "x-access-token",
                "Basic YXBpa2V5OjY5Y1dxVW8wNGhpNFdMdUdBT2IzMmRXZXQjsllsVzBtSkNiwU9yLUxEamNDUXFMSzJnR29mS3plZg=="
            )
            .build()
        return chain.proceed(modifiedRequest)
    }
}
