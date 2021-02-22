package com.example.lib_base.event

import org.greenrobot.eventbus.EventBus

object EventManage {
    //注册
    fun register(subscriber:Any){
        EventBus.getDefault().register(subscriber)
    }
    //解绑
    fun unRegister(subscriber:Any){
        EventBus.getDefault().unregister(subscriber)
    }

   //发送事件类
  private  fun post(event: MessageEvent){
        EventBus.getDefault().post(event)
    }
  //----------------------------公共类型-----------//
    //发送类型
   fun post(type:Int){
       post(MessageEvent(type))
   }
    //发送类型  携带String
    fun post (type:Int,string: String){
        val event=MessageEvent(type)
        event.stringValue=string
        post(event)
    }
    //发送类型  携带boolean
    fun post (type:Int,int: Int){
        val event=MessageEvent(type)
        event.intValue=int
        post(event)
    }
    //发送类型  携带boolean
    fun post (type:Int,boolean: Boolean){
        val event=MessageEvent(type)
        event.booleanValue=boolean
        post(event)
    }

}