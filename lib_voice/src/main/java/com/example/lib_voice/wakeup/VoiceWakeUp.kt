package com.example.lib_voice.wakeup

import android.content.Context
import com.baidu.speech.EventListener
import com.baidu.speech.EventManager
import com.baidu.speech.EventManagerFactory
import com.baidu.speech.asr.SpeechConstant
import org.json.JSONObject

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/23 17:58
 * 描述: 语音唤醒
 */
object VoiceWakeUp {
//启动数据
private lateinit var wakeUpJson:String
//唤醒对象
private lateinit var  wp: EventManager

//初始化唤醒
    fun ininWakeUp(mContext:Context,listener:EventListener){
        val map = HashMap<Any, Any>()
        //本地语音唤醒文件路径
        map[SpeechConstant.WP_WORDS_FILE] = "assets:///WakeUp.bin"
        //是否获取音量
        map[SpeechConstant.ACCEPT_AUDIO_VOLUME] = false
        //转换成Json
        wakeUpJson = JSONObject(map as Map<Any, Any>).toString()

        //设置一个监听器
    wp  = EventManagerFactory.create(mContext, "wp")
    //this是Activity或其它Context类
    //注册输出
    wp.registerListener(listener)
    }
    //启动唤醒
    fun startWakeUp(){
        wp.send(SpeechConstant.WAKEUP_START, wakeUpJson, null, 0, 0);
    }
     //停止唤醒
    fun stopWakeUp(){
        wp.send(SpeechConstant.WAKEUP_STOP, null, null, 0, 0);
    }

}