package com.example.lib_network.impl

import com.example.lib_network.http.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/26 15:54
 * 描述: 接口服务
 */
interface HttpInplServer{

   @GET(HttpUrl.WEATHER_ACTION)
   fun getWeather( @Query("city") city: String, @Query("key") Key:String): Call<ResponseBody>

}
