package com.example.lib_base.helper.`fun`

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import com.example.lib_base.helper.`fun`.data.AppData
import com.example.lib_base.utils.L
import java.util.ArrayList

/**
 * 作者: QQ:1396797522
 * 时间: 2021/3/3 10:21
 * 描述: 应用帮助类
 */
@SuppressLint("StaticFieldLeak")
object AppHelper {
    //上下文
    private lateinit var mContext: Context

    //包管理器
    private lateinit var pm: PackageManager

    private val mAllAPPList = ArrayList<AppData>()

    //初始化
    fun initHelper(mContext: Context) {
        this.mContext = mContext

        pm = mContext.packageManager//查找全局软件包信息
        loadAllAPP()

    }


    //加载所有的App
    fun loadAllAPP() {
        //应用程序入口
        val intent = Intent(Intent.ACTION_MAIN, null)

        intent.addCategory(Intent.CATEGORY_LAUNCHER) //应用程序显示在列表里
        val appInfo = pm.queryIntentActivities(intent, 0) //查询手机里可启动的应用  查询那些应用有intent
        //返回的是一个应用集合
        //遍历应用
        appInfo.forEachIndexed { _, resolveInfo ->

            val appData = AppData(
                resolveInfo.activityInfo.packageName,//获取包名
                resolveInfo.loadLabel(pm) as String, //应用名称
                resolveInfo.loadIcon(pm),//应用图标
                resolveInfo.activityInfo.name,//第一启动类
                resolveInfo.activityInfo.flags == ApplicationInfo.FLAG_SYSTEM  //   是不是系统app 返回false表示不是系统APP)
            )
            mAllAPPList.add(appData)
        }

        L.e("手机里的应用:${mAllAPPList}")

    }

    //启动APP
    fun Starttheapp(appName: String): Boolean {
        if (mAllAPPList.size > 0) {//在有应用的情况下
            mAllAPPList.forEach { //遍历所有应用
                if (it.appName == appName) { //启动那个应用
                    intentApp(it.packNane)//通过包名启动app
                    return true
                }
            }
        }
        return false
    }

    //卸载APP
    fun Uninstallapp(appName: String): Boolean {
        if (mAllAPPList.size > 0) {//在有应用的情况下
            mAllAPPList.forEach { //遍历所有应用
                if (it.appName == appName) { //启动那个应用
                    intenUnInstallApp(it.packNane)//通过包名启动app
                    return true
                }
            }
        }
        return false
    }

    //卸载APP基类
    private fun intenUnInstallApp(packageName: String) {
        val intent = Intent(Intent.ACTION_DIAL)//系统行动指令 //调用系统卸载
        intent.data = Uri.parse("package:${packageName}")
        mContext.startActivity(intent)
    }

    //启动APP基类
    private fun intentApp(packageName: String) {
        val intent = pm.getLaunchIntentForPackage(packageName)//获取包名启动
        intent?.let {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(it)
        }

    }

    //跳转应用商店
    fun intentAppStore(packageName: String, markPackageName: String) {
        val uri = Uri.parse("market://detaile?id=${packageName}")
        val intent = Intent(Intent.ACTION_VIEW, uri)//会根据用户的数据类型打开相应的Activity
        intent.setPackage(markPackageName)//跳转商店
        mContext.startActivity(intent)
    }

}