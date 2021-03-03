package com.example.lib_base.helper.`fun`.data

import android.graphics.drawable.Drawable
import java.io.StringReader

/**
 * 作者: QQ:1396797522
 * 时间: 2021/3/3 10:41
 * 描述:
 */

/**
 * 包名
 * 应用名称
 * ICON
 * 第一启动类
 * 是否是系统应用
 */
data class AppData(
    val packNane: String,
    val appName: String, val appIcon: Drawable,
    val fistRunName:String,
    val isSystemApp:Boolean
)