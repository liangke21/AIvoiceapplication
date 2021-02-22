package com.example.lib_base.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib_base.BuildConfig

/**
 * 作者: 13967
 * 时间: 2021/2/8 10:50
 * 描述: 路由帮助类
 */
object ARouterHelper {

    //Module   Path  路径
    const val PATH_APP_MANAGER="/app_manager/app_manager_activity"
    const val PATH_CONSTELLATION="/constellation/constellation_activity"
    const val PATH_DEVELOPER="/developer/developer_activity"
    const val PATH_JOKE="/joke/joke_activity"
    const val PATH_MAP="/map/map_activity"
    const val PATH_SETTING="/setting/setting_activity"
    const val PATH_VOVCE_SETTING="/voice_setting/voice_setting_activity"
    const val PATH_WEATHER="/weather/weather_activity"



     //初始化
    fun initHelper(application: Application){
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application)// 尽可能早，推荐在Application中初始化

    }
    //跳转页面
    fun startActivity(path: String){
        ARouter.getInstance().build(path).navigation()
    }

    fun startActivity(activity: Activity,path: String,requestCode:Int){
        ARouter.getInstance().build(path).navigation(activity,requestCode)
    }


    //跳转页面 携带String
    fun startActivity(path: String,key:String,value:String){
        ARouter.getInstance().build(path)
            .withString(key, value)
            .navigation()
    }
    //跳转页面 携带int
    fun startActivity(path: String,key:String,value:Int){
        ARouter.getInstance().build(path)
            .withInt(key, value)
            .navigation()
    }
    //跳转页面 boolean
    fun startActivity(path: String,key:String,value:Boolean){
        ARouter.getInstance().build(path)
            .withBoolean(key, value)
            .navigation()
    }
    //跳转页面 Bundle
    fun startActivity(path: String,key:String,bundle: Bundle){
        ARouter.getInstance().build(path)
            .withBundle(key, bundle)
            .navigation()
    }

    //跳转页面 Any也就是object
    fun startActivity(path: String,key:String,any: Any){
        ARouter.getInstance().build(path)
            .withObject(key, any)
            .navigation()
    }





}
