package com.example.lib_voice.asr

import android.content.Context
import com.baidu.speech.EventListener
import com.baidu.speech.EventManager
import com.baidu.speech.EventManagerFactory
import com.baidu.speech.asr.SpeechConstant
import com.example.lib_voice.TTs.VoiceTTs
import com.example.lib_voice.wakeup.VoiceWakeUp
import org.json.JSONObject

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/23 20:59
 * 描述:  语音识别
 */
object VoiceAsr {
    //识别对象
    private lateinit var  asr: EventManager

    private lateinit var wakeUpJson:String

fun initAsr(mContext: Context, listener: EventListener){

    val map = HashMap<Any, Any>()
    //是否获取音量
    map[SpeechConstant.ACCEPT_AUDIO_VOLUME] = true
    //音频数据
    map[SpeechConstant.ACCEPT_AUDIO_DATA] = false
    map[SpeechConstant.DISABLE_PUNCTUATION] = false
    map[SpeechConstant.PID] = 15363 //15373
    //转换成Json
wakeUpJson = JSONObject(map as Map<Any, Any>).toString()

    asr = EventManagerFactory.create(mContext, "asr")

    asr.registerListener(listener);
}

    //开始识别
    fun startAsr() {
        asr.send(SpeechConstant.ASR_START, wakeUpJson, null, 0, 0)

    }

    //控制停止识别
    fun stopAsr() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0)
    }


    //控制取消识别
    fun cancelAsr() {
        asr.send(SpeechConstant.ASR_CANCEL, null, null, 0, 0)
    }


    //释放
    fun releaseAsr(listener: EventListener) {
        asr.unregisterListener(listener)
    }

}