package com.example.lib_base.helper.`fun`

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lib_base.R
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

    //所有应用
    private val mAllAPPList = ArrayList<AppData>()

    //所有View
    val mAllViewList = ArrayList<View>()

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

        initPageView()

    }

    private fun initPageView() {
        for (i in 0 until getPageSize()) {
            // -> FrameLayout
            val rootView =
                View.inflate(mContext, R.layout.layout_app_manager_item, null) as ViewGroup
            //填充数据
            // -> 第一层 线性布局
            for (j in 0 until rootView.childCount) {
                // -> 第二层 六个线性布局
                val childX = rootView.getChildAt(j) as ViewGroup
                //  L.e("$j")
                // -> 第三层 四个线性布局
                for (k in 0 until childX.childCount) {
                    // -> 第四层 两个View ImageView TextView
                    val child = childX.getChildAt(k) as ViewGroup

                    val iv = child.getChildAt(0) as ImageView//图片
                    val tv = child.getChildAt(1) as TextView//文本
                    //计算你当前的下标
                    val index = i * 24 + j * 4 + k
                    //  L.e("$index")
                    if (index < mAllAPPList.size) {
                        //获取数据
                        val data = mAllAPPList[index]
                        tv.text = data.appName
                        iv.setImageDrawable(data.appIcon)
                        //点击事件
                        child.setOnClickListener {
                            Starttheapp(data.appName)
                        }
                    }
                }
            }
            mAllViewList.add(rootView)
        }
    }

    //获取页面数量
    fun getPageSize(): Int {
        return mAllAPPList.size / 24 + 1
    }

    //获取非系统应用
    fun getNotSystemApp(): List<AppData> {
        return mAllAPPList.filter {
            !it.isSystemApp
        }
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
        val uri = Uri.parse("package:${packageName}")
        val intent = Intent(Intent.ACTION_DELETE)//系统行动指令 //调用系统卸载
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK  //请出活动
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