package com.example.aivoikeapp

import android.content.Intent
import com.example.aivoikeapp.service.VoiceService
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.helper.ARouterHelper
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


class MainActivity :BaseActitvity() {


    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun getTitleText(): String {
       return getString(com.example.lib_base.R.string.app_name)
    }


    override fun isShowBack(): Boolean {
      return false
    }


    override fun initView() {
   startService(Intent(this, VoiceService::class.java))



        AndPermission.with(this)
            .runtime()
            .permission(Permission.RECORD_AUDIO)
            .onGranted{ ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPER)}
            .start()
    }
}
































































































  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn3.setOnClickListener {

            ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
        }





        EventManage.register(this)
     btn1.setOnClickListener {
        EventManage.post(1111)

     }
        btn2.setOnClickListener {
            EventManage.post(2222,"你好世界")
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        EventManage.unRegister(this)
    }


                            //线程默认主线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) { *//* Do something *//*

     when(event.type){
         1111-> Log.i("TsetApp","1111")
         2222-> Log.i("TsetApp",event.stringValue)
     }






    }






}*/