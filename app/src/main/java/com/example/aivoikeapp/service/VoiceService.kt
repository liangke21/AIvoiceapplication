package com.example.aivoikeapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.lib_base.helper.NotificationHelper
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

/**
 * 作者: 13967
 * 时间: 2021/2/19 12:54
 * 描述: 语音服务
 */
class VoiceService : Service(), OnNluResultListener {
    private val TGA = VoiceService::class.java.simpleName
    override fun onBind(intent: Intent?): IBinder? {
        return null//用不上说以返回空
    }


    override fun onCreate() {
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
  private  fun initCoreVoiceService(){
        L.i("${TGA}:语音启动服务2")
       VoiceManager.initManager(this,object : OnAsrResuLtListener{

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
           }

           override fun wakeUpSuccess(result: JSONObject) {
               L.i("唤醒成功: ${result}")
               //当唤醒词是二花的时候
               val errorCode = result.optInt("errorCode")
               //唤醒成功
               if (errorCode==0){
                  // 唤醒词
                   val word = result.optString("word")
                   if (word == "二花二花"){
                       //应答
                       //百度应答
                     /* VoiceManager.TTstart(WordsTools.wakeupWords(),object : VoiceTTs.OnTTSResultListener{
                           override fun ttsEnd() {
                               //百度识别
                               VoiceManager.startAsr()
                           }
                       })*/
                       //讯飞应答
                     xunfeiTTs.start(WordsTools.wakeupWords(),object : xunfeiTTs.XFOnTTSResultListener{
                          override fun ttsEnd() {
                              //百度识别
                              VoiceManager.startAsr()
                          }
                      })

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
               //this@VoiceService 是在这个类里面实现了
               VoiceEngineAnaly.analyzeNlu(nlu,this@VoiceService)
           }

           override fun voiceError(texe: String) {
               L.e("发生错误${texe}")
           }

       })
    }
  //查询天气
    override fun queryWeather() {

    }


}
