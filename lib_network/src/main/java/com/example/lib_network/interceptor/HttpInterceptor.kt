package com.example.lib_network.interceptor

import android.nfc.Tag
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/26 16:22
 * 描述: 拦截器 /重定向 请求头 加密/解密
 */
class HttpInterceptor : Interceptor{
    private val TAG="HTTP"
    override fun intercept(chain: Interceptor.Chain): Response {
       //请求参数
        val request = chain.request()
        val response = chain.proceed(request)

       Log.i(TAG,"=========REQUEST==========")
        if(request.method()=="GET"){
            Log.i(TAG,request.url().toString())
            }


        Log.i(TAG,"=========REQUEST==========")
        response.body()?.let {
            Log.i(TAG,it?.string())
        }


        return response
    }
}
