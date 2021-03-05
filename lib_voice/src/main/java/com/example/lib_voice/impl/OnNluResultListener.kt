package com.example.lib_voice.impl

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/24 13:04
 * 描述: 语义结果
 */
interface OnNluResultListener {

    //==================App 操作====================
    //打开APP
    fun openApp(AppName: String)

    //卸载App
    fun unInstallApp(AppName: String)

    //其他app
    fun otherApp(AppName: String)

    //查询天气
    fun queryWeather()


    //听不懂你的话
    fun nlnError()
}
