package com.example.lib_voice.TTs

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import com.baidu.tts.client.SpeechError
import com.baidu.tts.client.SpeechSynthesizer
import com.baidu.tts.client.SpeechSynthesizerListener
import com.baidu.tts.client.TtsMode
import com.example.lib_voice.manager.VoiceManager


/**
 * 作者: 13967
 * 时间: 2021/2/19 15:34
 * 描述: 百度Ai语音 - TTs 封装
 *
 * 1.实现其它参数
 * 2.实现监听播放结束
 *
 */
object VoiceTTs : SpeechSynthesizerListener {

    /**
     * 假设:我们有一个需求
     * 就是当TTs播放结束的时候执行一段 操作
     */

    private var TAG = VoiceTTs::class.java.simpleName //打印日志


    //TTs对象
    private lateinit var mSpeechSynthesizer: SpeechSynthesizer

   private var mOnTTSResultListener:OnTTSResultListener? = null
    //初始化TTs
    fun initTTS(mContext: Context) {
//初始化对象
        mSpeechSynthesizer = SpeechSynthesizer.getInstance()

        //设置上下文
        mSpeechSynthesizer.setContext(mContext)

        //设置Key 设置 App Id和 App Key 及 App Secret
        mSpeechSynthesizer.setAppId(VoiceManager.VOICE_APP_ID)
        mSpeechSynthesizer.setApiKey(VoiceManager.VOICE_APP_KEY, VoiceManager.VOICE_APP_SECRET)
        //设置监听
        mSpeechSynthesizer.setSpeechSynthesizerListener(this)//注意重写


        //其他参数
        //发声人
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, 0.toString())
        //语速
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, 5.toString())
        //音量
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, 5.toString())
        //初始化
        mSpeechSynthesizer.initTts(TtsMode.ONLINE)//在线的

    }

    override fun onSynthesizeStart(p0: String?) {
        Log.i(TAG, "合成开始")
    }

    override fun onSynthesizeDataArrived(p0: String?, p1: ByteArray?, p2: Int, p3: Int) {
        //合成过程中的数据回调接口
    }

    override fun onSynthesizeFinish(p0: String?) {
        Log.i(TAG, "合成结束")
    }

    override fun onSpeechStart(p0: String?) {
        Log.i(TAG, "开始播放")
    }

    override fun onSpeechProgressChanged(p0: String?, p1: Int) {
        //播放过程中的回调
    }

    override fun onSpeechFinish(p0: String?) {
        Log.i(TAG, "播放结束")

        mOnTTSResultListener?.ttsEnd()
    }

    override fun onError(p0: String?, p1: SpeechError?) {
        Log.e(TAG, "TTS错误: $p0:$p1")
        //合成和播放过程中出错时的回调
    }



    //播放而且有回调
    fun start(text: String,mOnTTSResultListener: OnTTSResultListener?) {
        this.mOnTTSResultListener = mOnTTSResultListener
        mSpeechSynthesizer.speak(text)
    }

    //暂停播放
    fun pause() {
        mSpeechSynthesizer.pause()
    }

    //继续播放
    fun resume() {
        mSpeechSynthesizer.resume()
    }

    //停止播放
    fun stop() {
        mSpeechSynthesizer.stop()
    }

    //释放
    fun release() {
        mSpeechSynthesizer.release()
    }

    //提供一个接口监听回调
    interface OnTTSResultListener{
        fun ttsEnd()
    }


}