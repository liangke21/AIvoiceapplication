package com.example.lib_base.utils

import android.util.Log
import com.example.lib_base.BuildConfig

/**
 * 作者: 13967
 * 时间: 2021/2/18 10:55
 * 描述: Log 日志
 */
object L {

  private  const val TAG:String="AivoiceApp"

    fun i(text:String?){
        if (BuildConfig.DEBUG){
           text?.let {
               Log.i(TAG,it)
           }
        }
    }


    fun e(text:String?){
        if (BuildConfig.DEBUG){
            text?.let {
                Log.e(TAG,it)
            }
        }
    }

}
