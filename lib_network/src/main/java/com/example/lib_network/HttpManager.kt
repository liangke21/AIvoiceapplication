package com.example.lib_network

import com.example.lib_network.http.HttpKey
import com.example.lib_network.http.HttpUrl
import com.example.lib_network.impl.HttpInplServer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/26 15:34
 * 描述: 对外的网络管理类
 */
object HttpManager {
                    //   lazy  在被调用的时候初始化
    private val retrofitWeather by lazy {
        Retrofit.Builder()
            .baseUrl(HttpUrl.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //天气接口对象
    private val apiweather by lazy {
        retrofitWeather.create(HttpInplServer::class.java)
    }

    //查询天气
    fun queryWeather(city:String):Call<ResponseBody>{
        return apiweather.getWeather(city,HttpKey.WEATHER_KEY)
    }
}