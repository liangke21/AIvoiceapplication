package com.example.lib_voice.manager

import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.example.lib_voice.TTs.VoiceTTs
import com.example.lib_voice.wakeup.VoiceWakeUp

/**
 * 作者: 13967
 * 时间: 2021/2/20 10:28
 * 描述:语音管理类
 */
object VoiceManager : EventListener {

    //语音 key

    const val VOICE_APP_ID = "23669108"
    const val VOICE_APP_KEY = "6HjKSkPMEmyLTyWBUyownxfB"
    const val VOICE_APP_SECRET = "HIqiojt81tuQjGWVjion4GjmUkamjh3j"

    private var TAG = VoiceManager::class.java.simpleName

    fun initManager(mContext: Context) {
        //初始化
        VoiceTTs.initTTS(mContext)
        VoiceWakeUp.ininWakeUp(mContext,this)
    }

    /*------------TTS语音合成------------------------------------------------------*/
    //播放
    fun TTstart(text: String) {
        Log.d(TAG,"TTS:开始播放${text}")

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
/*------------TTS语音合成------------------------------------------------------*/
/*------------语音唤醒合成------------------------------------------------------*/
//启动唤醒
    fun startWakeUp(){
    Log.d(TAG,"WakeUp:启动唤醒")
        VoiceWakeUp.startWakeUp()
    }
//停止唤醒
    fun stopWakeUp(){
    Log.d(TAG,"WakeUp:停止唤醒")
        VoiceWakeUp.stopWakeUp()
    }

    /*------------语音唤醒合成------------------------------------------------------*/

    override fun onEvent( name:String?,  params:String?,  data : ByteArray?, offset:Int,  length:Int) {
    Log.d(TAG, String.format("event: name=%s, params=%s", name, params))

        name?.let {
            when(it){
               com.baidu.speech.asr.SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS -> VoiceManager.TTstart("我在")
            }
        }
    }


}