package com.example.lib_base.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * 作者: 13967
 * 时间: 2021/2/18 15:18
 * 描述: sp 封装
 */
object SpUtils {
const val SP_NAME="config"//文件名
    //对象
    private lateinit var sp: SharedPreferences

    private lateinit var spEditor:SharedPreferences.Editor

//初始化
    fun initUtils(mContext:Context){
   sp=mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE)//私有的//获取SharedPreferences实例
        spEditor=sp.edit()//edit()方法获取一个SharedPreferences.Editor对象。
    //SharedPreferences.Editor对象中添加数据。
        spEditor.apply()//提交数据
    }

    //键和字符
    fun putString(key:String,value:String){
        spEditor.putString(key, value)
        spEditor.commit()//提交
    }
    //键和可空
    fun getString(key:String):String?{
        return sp.getString(key,"")
    }


    //键和int
    fun putString(key:String,value:Int){
        spEditor.putInt(key, value)
        spEditor.commit()//提交
    }
    //键和可空
    fun getString(key:String, devalue:Int):Int?{
        return sp.getInt(key,devalue)
    }


}