package com.example.aivoikeapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.lib_base.helper.NotificationHelper
import com.example.lib_base.utils.L
import com.example.lib_voice.manager.VoiceManager
import java.security.Provider

/**
 * 作者: 13967
 * 时间: 2021/2/19 12:54
 * 描述: 语音服务
 */
class VoiceService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null//用不上说以返回空
    }


    override fun onCreate() {
        super.onCreate()
        L.i("语音启动服务")
        initService()
    }

    //初始化服务
    private fun initService() {

        VoiceManager.initManager(this)


    }


    /**
     *START_STICKY:当系统内存不足的时候,杀掉服务,那么在系统紧张的时候才启动服务
     * START_NOT_STICKY: 当系统内存不足的时候,杀掉了服务,直达下一次startServicece才启动
     * START_REDELIVER_INTENT: 重新传递Intent值
     * START_STICKY_COMPATIBILITY: START_STICKY兼容版本,但是它也不能保证系统killd掉服务一定能重启
     */
    //后台启动
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNontification()
        return START_STICKY_COMPATIBILITY
    }

    //绑定通知栏
    private fun bindNontification() {
        //绑定通知栏
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行"))
    }


}
