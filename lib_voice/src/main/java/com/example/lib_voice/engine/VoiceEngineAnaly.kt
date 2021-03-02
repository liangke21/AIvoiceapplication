package com.example.lib_voice.engine

import android.nfc.Tag
import android.util.Log
import com.example.lib_voice.impl.OnNluResultListener
import com.example.lib_voice.xunfeiTTS.xunfeiTTs
import com.imooc.lib_voice.words.NluWords
import com.imooc.lib_voice.words.WordsTools
import org.json.JSONObject

/**
 * 作者: QQ:1396797522
 * 时间: 2021/2/24 12:28
 * 描述:
 */
object VoiceEngineAnaly {

    private var TAG = VoiceEngineAnaly::class.java.simpleName

    private lateinit var mOnNluResultListener: OnNluResultListener

    //分析结果
    fun analyzeNlu(nlu: JSONObject, mOnNluResultListener: OnNluResultListener) {

        this.mOnNluResultListener = mOnNluResultListener
        //用户说的话
        val rawText = nlu.optString("raw_text")
        Log.i(TAG, "rawText:${rawText}")

        //解析result

        var result = nlu.optJSONArray("results") ?: return

        val nluResultLength = result.length()

        when {
            nluResultLength <= 0 -> 0//  xunfeiTTs.start(WordsTools.wakeupWords())
            //单条命中
            result.length() == 1 -> analyzeNluSingle(result[0] as JSONObject)
        }
    }

    private fun analyzeNluSingle(result: JSONObject) {
        val domain = result.optString("domain")//领域
        val intent = result.optString("intent")//意图
        val slots = result.optJSONObject("slots")//插槽

        when (domain) {
            NluWords.NLU_WEATHER -> {
                // 获取其他类型
            }

        }

    }
}