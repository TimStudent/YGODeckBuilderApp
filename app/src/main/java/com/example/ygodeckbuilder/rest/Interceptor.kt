package com.example.ygodeckbuilder.rest

import android.content.ContentValues.TAG
import android.util.Log
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

//private const val TAG = "Interceptors"
//class Interceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val response = chain.proceed(request)
//
//        val cacheControl = CacheControl.Builder()
//            .maxAge(5, TimeUnit.MINUTES)
//            .build()
//
//        return response.newBuilder()
//            .header("MY_CACHE", cacheControl.toString())
//            .build()
//    }
//}
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("TOKEN", "THis is your oAuthToken")
                addHeader("Env", "QA")
                addHeader("version", "1.0")
            }.build()
        )
}
//
class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).apply {
            if (this.code == 200) {
                Log.d(TAG, "intercept: SUCCESS RESPONSE")
            } else {
                Log.e(TAG, "intercept: Failure Response", )
            }
        }
    }
}