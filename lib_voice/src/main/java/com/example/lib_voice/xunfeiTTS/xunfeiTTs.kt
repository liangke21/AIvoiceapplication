package com.example.lib_voice.xunfeiTTS

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.iflytek.cloud.*


/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/20 13:02
 * 描述:  讯飞语音
 */
object xunfeiTTs {
    private val TAG: String = xunfeiTTs::class.java.simpleName


    //合成语音对象
    private lateinit var mTts: SpeechSynthesizer


    // 默认发音人
   // private const val voicer = "x2_yifei"  //2021-03-06 有效期
    private const val voicer ="x2_xiaorong"
//播放有回调接口
   private  var mXFOnTTSResultListener:XFOnTTSResultListener? = null


    fun initialled(context: Context) {
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=5d785b55")

        // 初始化合成对象
        mTts = SpeechSynthesizer.createSynthesizer(context) { code ->
            if (code != ErrorCode.SUCCESS) {
                Log.d("tag54", "初始化失败,错误码：$code")
            }
            Log.d("tag54", "初始化失败,q错误码：$code")
        }

        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, voicer)
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, 50.toString())
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, 50.toString())
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, 50.toString())


        //初始化语音识别
        //初始化语音识别


    }


    /**
     * 合成回调监听。
     */
    private val mTtsListener: SynthesizerListener = object : SynthesizerListener {
        override fun onSpeakBegin() {
            Log.i(TAG, "开始播放")
        }

        override fun onSpeakPaused() {
            Log.i(TAG, "暂停播放")
        }

        override fun onSpeakResumed() {
            Log.i(TAG, "继续播放")
        }

        override fun onBufferProgress(progress: Int, beginPos: Int, endPos: Int, info: String) {

            // 合成进度

        }

        override fun onSpeakProgress(percent: Int, beginPos: Int, endPos: Int) {
            // 播放进度
        }

        override fun onCompleted(error: SpeechError?) {
            Log.i(TAG, "完成时")
            mXFOnTTSResultListener?.ttsEnd()
        }

        override fun onEvent(eventType: Int, arg1: Int, arg2: Int, obj: Bundle) {
            Log.i(TAG, "tts出错$eventType:$eventType:$arg2:$obj")
        }
    }


    //播放
    fun start(text: String) {
        mTts.startSpeaking(text, mTtsListener)
    }

    //播放并且回调
    fun start(text: String, mXFOnTTSResultListener: XFOnTTSResultListener) {
        this.mXFOnTTSResultListener = mXFOnTTSResultListener
        mTts.startSpeaking(text, mTtsListener)
    }

    //暂停播放
    fun pause() {
        mTts.pauseSpeaking()
    }

    //继续播放
    fun resume() {
        mTts.resumeSpeaking()
    }

    //停止播放
    fun stop() {
        mTts.stopSpeaking()
    }



    //提供一个接口监听回调
    interface XFOnTTSResultListener {
        fun ttsEnd()
    }


}