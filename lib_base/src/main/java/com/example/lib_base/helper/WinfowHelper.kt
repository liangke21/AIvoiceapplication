package com.example.lib_base.helper

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.os.Build.VERSION_CODES.O
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * 作者: QQ:1396797522
 * 时间: 2021/3/2 12:54
 * 描述: 窗口帮助类
 */
object WinfowHelper {

    private lateinit var mContext:Context
    private lateinit var wm:WindowManager
    private lateinit var lp:WindowManager.LayoutParams

fun initHelper(mContext:Context){

    this.mContext=mContext

    wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager //初始化
 lp =   createLayoutParams()

}

    /**
     * 创建窗口布局
     * width : 窗口的宽
     * height : 窗口的高
     *  gravity: 是否居中
     */
 private   fun createLayoutParams():WindowManager.LayoutParams{
    val lp =    WindowManager.LayoutParams() //获取窗口布局
        lp.apply {
          this.width =  WindowManager.LayoutParams.MATCH_PARENT
            this.height = WindowManager.LayoutParams.MATCH_PARENT
            gravity = Gravity.CENTER //居中

            format = PixelFormat.TRANSLUCENT //设置图片背景效果为透明
            flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
          type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
              WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY //应用程序覆盖
          }else{
              WindowManager.LayoutParams.TYPE_PHONE // 电话窗口
          }
        }
 return  lp
    }
//获取视图控件
    fun getView(layoutId:Int):View{
        return View.inflate(mContext,layoutId,null)
    }
    //显示窗口
   fun show(view:View){
       if (view.parent==null){
           wm.addView(view,lp)
       }
   }

    //显示窗口 ,但是窗口属性自定义
    fun show(view:View,lp:WindowManager.LayoutParams){
        if (view.parent==null){
            wm.addView(view,lp)
        }
    }

    //隐藏视图
    fun hide(view:View){
        if (view.parent!=null){
            wm.removeView(view)
        }
    }

    //更新视图
    fun update(view:View,lp:WindowManager.LayoutParams){
        if (view.parent!=null){
            wm.updateViewLayout(view,lp)
        }
    }

}
