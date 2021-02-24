package com.example.lib_voice.impl

import org.json.JSONObject

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/24 9:12
 * 描述: 语音识别接口
 */
interface  OnAsrResuLtListener {

    //唤醒准备就绪
    fun wakeUpReady()
    //开始说话
    fun asrSrartSpeak()


    //停止说话
    fun asrStopSpeak()
    //唤醒成功
    fun wakeUpSuccess(result:JSONObject)

    //在线识别结果

    fun asrREsult(result: JSONObject)

    //意义识别结果

    fun nluResult(nlu:JSONObject)

    //错误
    fun voiceError(texe:String)
}