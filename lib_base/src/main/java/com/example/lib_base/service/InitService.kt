package com.example.lib_base.service

import android.app.IntentService
import android.content.Intent
import com.example.lib_base.helper.NotificationHelper
import com.example.lib_base.helper.SoundPoolHelper
import com.example.lib_base.utils.L
import com.example.lib_base.utils.SpUtils
import com.example.lib_voice.manager.VoiceManager
import com.example.lib_voice.xunfeiTTS.xunfeiTTs
import com.imooc.lib_voice.words.WordsTools

/**
 * 作者: 13967
 * 时间: 2021/2/19 9:54
 * 描述: 初始化服务
 */
class InitService : IntentService(InitService::class.java.simpleName) {
    override fun onCreate() {
        super.onCreate()
        L.i("IntentService:初始化开始")
    }

    override fun onHandleIntent(intent: Intent?) {
        L.i("IntentService:初始化操作")

        //sp初始化
        SpUtils.initUtils(this)
        //通知栏初始化
        NotificationHelper.initHelper(this)
        //TTS语音在线合成初始化
        //去前台初始化了  loiceManager.initManager(this)
        //讯飞tts语音
        xunfeiTTs.initialled(this)

        //词条初始化
        WordsTools.initTools(this)
        //播放初始化
        SoundPoolHelper.init(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        L.i("IntentService:初始化完成")
    }
}

