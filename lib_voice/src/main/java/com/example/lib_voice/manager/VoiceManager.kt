package com.example.lib_voice.manager

import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.baidu.speech.asr.SpeechConstant
import com.example.lib_voice.TTs.VoiceTTs
import com.example.lib_voice.asr.VoiceAsr
import com.example.lib_voice.impl.OnAsrResuLtListener
import com.example.lib_voice.wakeup.VoiceWakeUp
import org.json.JSONObject

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

    //接口
    private lateinit var mOnAsrResuLtListener: OnAsrResuLtListener

    fun initManager(mContext: Context, mOnAsrResuLtListener: OnAsrResuLtListener) {

        this.mOnAsrResuLtListener = mOnAsrResuLtListener
        //初始化语音合成
        VoiceTTs.initTTS(mContext)
        //初始化本地唤醒
        VoiceWakeUp.ininWakeUp(mContext, this)
        //初始化语音识别
        VoiceAsr.initAsr(mContext, this)
    }

    /*------------TTS语音合成------------------------------------------------------*/
    //播放
    fun TTstart(text: String) {
        Log.d(TAG, "TTS:开始播放${text}")

        VoiceTTs.start(text, null)
    }

    fun TTstart(text: String, mOnTTSResultListener: VoiceTTs.OnTTSResultListener) {
        VoiceTTs.start(text, mOnTTSResultListener)
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
        VoiceTTs.setVicepeople(string)
    }

    //设置语速
    fun setVicecpeed(string: String) {

        VoiceTTs.setVicecpeed(string)

    }

    //设置音量
    fun setViceVolume(string: String) {
        VoiceTTs.setViceVolume(string)

    }

    /*------------TTS语音合成------------------------------------------------------*/
/*------------语音唤醒合成------------------------------------------------------*/
//启动唤醒
    fun startWakeUp() {
        Log.d(TAG, "WakeUp:启动唤醒")
        VoiceWakeUp.startWakeUp()
    }

    //停止唤醒
    fun stopWakeUp() {
        Log.d(TAG, "WakeUp:停止唤醒")
        VoiceWakeUp.stopWakeUp()
    }

    /*------------语音唤醒合成------------------------------------------------------*/

    /*----------------语音识别-----------------------------------------*/


    //开始识别
    fun startAsr() {
        VoiceAsr.startAsr()

    }

    //控制停止识别
    fun stopAsr() {
        VoiceAsr.stopAsr()
    }


    //控制取消识别
    fun cancelAsr() {
        VoiceAsr.cancelAsr()
    }


    //释放
    fun releaseAsr() {
        VoiceAsr.releaseAsr(this)
    }

/*----------------语音识别-----------------------------------------*/


    override fun onEvent(
        name: String?,
        params: String?,
        data: ByteArray?,
        offset: Int,
        length: Int
    ) {
        // Log.d(TAG, String.format("event: name=%s, params=%s", name, params))


        when (name) {
            SpeechConstant.CALLBACK_EVENT_WAKEUP_READY -> mOnAsrResuLtListener.wakeUpReady()//唤醒准备就绪
            SpeechConstant.CALLBACK_EVENT_ASR_BEGIN -> mOnAsrResuLtListener.asrSrartSpeak()//开始说话
            SpeechConstant.CALLBACK_EVENT_ASR_END -> mOnAsrResuLtListener.asrStopSpeak()//结束说话
        }

        if (params == null) {
            return
        }

        val allIson = JSONObject(params)
        when (name) {
            SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS -> mOnAsrResuLtListener.wakeUpSuccess(
                allIson
            ) // 唤醒后的回调
            SpeechConstant.CALLBACK_EVENT_WAKEUP_ERROR -> Log.i(TAG, "唤醒失败")
            SpeechConstant.CALLBACK_EVENT_ASR_FINISH -> mOnAsrResuLtListener.asrREsult(allIson)
            SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL -> {
                mOnAsrResuLtListener.updateUserText(allIson.optString("bset_result"))
                data?.let {
                    val nlu = JSONObject(String(data, offset, length))
                    Log.e(TAG, "ASR动作识别结果:${nlu}")
                    mOnAsrResuLtListener.nluResult(nlu)

                }
            }
        }

    }


}