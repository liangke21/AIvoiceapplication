package com.example.aivoikeapp.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aivoikeapp.R
import com.example.aivoikeapp.adapter.ChatListAdapter
import com.example.aivoikeapp.data.ChatLis
import com.example.aivoikeapp.entity.AppConstanst
import com.example.lib_base.helper.NotificationHelper
import com.example.lib_base.helper.WinfowHelper
import com.example.lib_base.utils.L
import com.example.lib_voice.TTs.VoiceTTs
import com.example.lib_voice.engine.VoiceEngineAnaly
import com.example.lib_voice.impl.OnAsrResuLtListener
import com.example.lib_voice.impl.OnNluResultListener
import com.example.lib_voice.manager.VoiceManager
import com.example.lib_voice.xunfeiTTS.xunfeiTTs
import com.imooc.lib_voice.words.WordsTools
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

/**
 * 作者: 13967
 * 时间: 2021/2/19 12:54
 * 描述: 语音服务
 */
class VoiceService : Service(), OnNluResultListener {
    private val TGA = VoiceService::class.java.simpleName

    private lateinit var mFullwindowView: View
    private lateinit var mChatListView: RecyclerView
    private val mList = ArrayList<ChatLis>()
    private lateinit var mChatListAdapter: ChatListAdapter


    override fun onBind(intent: Intent?): IBinder? {
        return null//用不上说以返回空
    }


    override fun onCreate() {
        L.i("${TGA}:初始化")
        super.onCreate()
        L.i("${TGA}:语音启动服务")
        initService()
        //初始化语音服务
        initCoreVoiceService()
    }

    //初始化服务
    private fun initService() {

        //讯飞tts语音
        xunfeiTTs.initialled(this)


    }


    /**
     *START_STICKY:当系统内存不足的时候,杀掉服务,那么在系统紧张的时候才启动服务
     * START_NOT_STICKY: 当系统内存不足的时候,杀掉了服务,直达下一次startServicece才启动
     * START_REDELIVER_INTENT: 重新传递Intent值
     * START_STICKY_COMPATIBILITY: START_STICKY兼容版本,但是它也不能保证系统killd掉服务一定能重启
     */
    //后台启动
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNontification()
        return START_STICKY_COMPATIBILITY
    }

    //绑定通知栏
    private fun bindNontification() {
        //绑定通知栏
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行"))
    }


    //初始化语音服务
    private fun initCoreVoiceService() {

        WinfowHelper.initHelper(this)//窗口初始化
        mFullwindowView = WinfowHelper.getView(R.layout.layou_window_item)
        mChatListView = mFullwindowView.findViewById<RecyclerView>(R.id.mChatListView)
        mChatListView.layoutManager = LinearLayoutManager(this)
        mChatListAdapter = ChatListAdapter(mList)
        mChatListView.adapter = mChatListAdapter


        VoiceManager.initManager(this, object : OnAsrResuLtListener {

            override fun wakeUpReady() {
                L.i("${TGA}:唤醒就绪")
                //   VoiceManager.TTstart("唤醒引擎准备就绪")//百度
                xunfeiTTs.start("唤醒引擎准备就绪")//讯飞
            }

            override fun asrSrartSpeak() {
                L.i("开始说话")
            }

            override fun asrStopSpeak() {
                L.i("结束说话")
            //    hideWindow()//隐藏窗口
            }

            override fun wakeUpSuccess(result: JSONObject) {
                L.i("唤醒成功: ${result}")
                //当唤醒词是二花的时候
                val errorCode = result.optInt("errorCode")
                //唤醒成功
                if (errorCode == 0) {
                    // 唤醒词
                    val word = result.optString("word")
                    if (word == "二花二花") {
                        showWindoW()//显示窗口
                        //应答
                        val wakeupText = WordsTools.wakeupWords()
                        addMineText(wakeupText)
                        //百度应答
                         VoiceManager.TTstart(wakeupText,object : VoiceTTs.OnTTSResultListener{
                              override fun ttsEnd() {
                                  //百度识别
                                  VoiceManager.startAsr()
                              }
                          })
                       /* //讯飞应答
                        xunfeiTTs.start(wakeupText,
                            object : xunfeiTTs.XFOnTTSResultListener {
                                override fun ttsEnd() {
                                    //百度识别
                                    VoiceManager.startAsr()
                                }
                            })*/

                    }
                }
            }

            override fun asrREsult(result: JSONObject) {
                L.i("---------------------result-------------------------------")
                L.i("识别结果: $result")

            }

            override fun nluResult(nlu: JSONObject) {
                L.i("---------------------result-------------------------------")
                L.i("识别出的Json结果:  $nlu")
                addAiText(nlu.toString())
                //this@VoiceService 是在这个类里面实现了
                VoiceEngineAnaly.analyzeNlu(nlu, this@VoiceService)
            }

            override fun voiceError(texe: String) {
                L.e("发生错误${texe}")
            //    hideWindow()//隐藏窗口
            }

        })
    }

    private val mHandler = Handler()

    //显示窗口
    private fun showWindoW() {
        L.i("=====显示窗口=====")
        WinfowHelper.show(mFullwindowView)
    }

    //隐藏窗口
    private fun hideWindow() {
        L.i("=====隐藏窗口=====")
        mHandler.postDelayed({
            WinfowHelper.hide(mFullwindowView)
        }, 2 * 1000)

    }

    //查询天气
    override fun queryWeather() {

    }

    /**
     * 添加我的文本
     *
     */
    private fun addMineText(text: String) {
        val bean = ChatLis(AppConstanst.TYPE_MINE_TEXT)
        L.e("启动我的文本:${bean.toString()}")
        bean.text = text
        L.e("启动我的文本:${bean.toString()}")
        baseAddItem(bean)
    }

    /**
     * Ai 文本
     */
    private fun addAiText(text: String) {
        val bean = ChatLis(AppConstanst.TYPE_AI_TEXT)
        L.e("启动Ai文本:${bean.toString()}")
        bean.text = text
        baseAddItem(bean)
    }


    private fun baseAddItem(bean: ChatLis) {
        mList.add(bean)
        mChatListAdapter.notifyItemInserted(mList.size - 1)//局部刷新
    }


}
