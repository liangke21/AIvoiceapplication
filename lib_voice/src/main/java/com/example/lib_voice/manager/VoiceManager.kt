package com.example.lib_voice.manager

import android.content.Context
import com.example.lib_voice.TTs.VoiceTTs

/**
 * 作者: 13967
 * 时间: 2021/2/20 10:28
 * 描述:语音管理类
 */
object VoiceManager {

    //语音 key

    const val VOICE_APP_ID = "23669108"
    const val VOICE_APP_KEY = "6HjKSkPMEmyLTyWBUyownxfB"
    const val VOICE_APP_SECRET = "HIqiojt81tuQjGWVjion4GjmUkamjh3j"


    fun initManager(mContext: Context) {
        //初始化
        VoiceTTs.initTTS(mContext)
    }


    //播放
    fun start(text: String) {
        VoiceTTs.start(text,null)
    }

    fun start(text: String,mOnTTSResultListener: VoiceTTs.OnTTSResultListener) {
        VoiceTTs.start(text,mOnTTSResultListener)
    }

    //暂停播放
    fun pause() {
        VoiceTTs.pause()
    }

    //继续播放
    fun resume() {
        VoiceTTs.resume()
    }

    //停止播放
    fun stop() {
        VoiceTTs.stop()
    }

    //释放
    fun release() {
        VoiceTTs.release()
    }


}