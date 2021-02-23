package com.example.lib_voice.manager

import android.content.Context
import com.baidu.tts.client.SpeechSynthesizer
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
    fun TTstart(text: String) {
        VoiceTTs.start(text,null)
    }

    fun TTstart(text: String,mOnTTSResultListener: VoiceTTs.OnTTSResultListener) {
        VoiceTTs.start(text,mOnTTSResultListener)
    }

    //暂停播放
    fun TTSpause() {
        VoiceTTs.pause()
    }

    //继续播放
    fun TTSresume() {
        VoiceTTs.resume()
    }

    //停止播放
    fun TTSstop() {
        VoiceTTs.stop()
    }

    //释放
    fun TTSrelease() {
        VoiceTTs.release()
    }




    //设置发声人
    fun setVicepeople(string: String) {
        VoiceTTs.setVicepeople( string)
    }

    //设置语速
    fun setVicecpeed(string: String) {

        VoiceTTs.setVicecpeed( string)

    }

    //设置音量
    fun setViceVolume(string: String) {
        VoiceTTs.setViceVolume( string)

    }



}