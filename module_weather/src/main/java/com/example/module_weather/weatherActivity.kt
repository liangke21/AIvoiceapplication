package com.example.module_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_WEATHER)
class weatherActivity : BaseActitvity() {
    override fun getLayoutId(): Int {
     return  R.layout.activity_weather
    }

    override fun getTitleText(): String {
      return getString(com.example.lib_base.R.string.app_title_weather)
    }

    override fun isShowBack(): Boolean {
      return true
    }

    override fun initView() {

    }
}