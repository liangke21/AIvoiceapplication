package com.example.module_joke

import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_JOKE)
class JokeActivity : BaseActitvity(){
    override fun getLayoutId(): Int {
       return R.layout.activity_joke
    }

    override fun getTitleText(): String {
      return getString(com.example.lib_base.R.string.app_title_joke)
    }

    override fun isShowBack(): Boolean {
       return true
    }

    override fun initView() {

    }
}