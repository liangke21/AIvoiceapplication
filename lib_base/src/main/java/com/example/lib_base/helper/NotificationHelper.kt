package com.example.lib_base.helper

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

/**
 * 作者: 13967
 * 时间: 2021/2/19 13:38
 * 描述: 通知栏的帮助类
 */

@SuppressLint("StaticFieldLeak")
object NotificationHelper {

    private lateinit var mContext: Context
    private lateinit var nm: NotificationManager

    private const val CHANNEL_ID = "ai_voice_service"
    private const val CHANNEL_NAME = "语音服务"

    //初始化帮助类
    fun initHelper(mContext: Context) {
        this.mContext = mContext
        //通知服务
        nm = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//创建渠道
        setBindVoiceChannel()


    }

    //设置绑定服务的渠道
    fun setBindVoiceChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建渠道对象                 第一个参数通道id  第二个参数显示通道名称   第三个参数渠道重要性
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            //呼吸灯
            channel.enableLights(false)
            //震动
            channel.enableVibration(false)
            //角标
            channel.setShowBadge(false)

            nm.createNotificationChannel(channel)//创建渠道
        }

    }

    //绑定语音服务
    fun bindVoiceService(ContentText: String): Notification {
        //创建通知栏对象
        val notificationCompat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(mContext, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(mContext)
        }
//设置标题
        notificationCompat.setContentTitle(CHANNEL_NAME)
        //设置描述
        notificationCompat.setContentText(ContentText)
        //设置时间
        notificationCompat.setWhen(System.currentTimeMillis())
        //禁止滑动 结束服务
        notificationCompat.setAutoCancel(false)
        return notificationCompat.build()
    }

}