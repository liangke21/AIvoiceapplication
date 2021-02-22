package com.example.lib_base.base
import android.app.Application
import android.content.Intent
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_base.service.InitService
import com.example.lib_base.utils.L
import com.example.lib_voice.manager.VoiceManager
import com.example.lib_voice.xunfeiTTS.xunfeiTTs

/**
 * 作者: 13967
 * 时间: 2021/2/8 11:12
 * 描述:
 */
open class BaseApp:Application() {

    override fun onCreate() {
        super.onCreate()
        L.i("Application:初始化")
        //软路由初始化
        ARouterHelper.initHelper(this)
        //短服务初始化
       startService(Intent(this,InitService::class.java))
        //通知栏初始化
      //  NotificationHelper.initHelper(this)
        //TTs语音初始化
      // VoiceManager.initManager(this)




    }

}
