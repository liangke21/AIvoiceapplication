package com.example.module_setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_SETTING)
class settingActivity : BaseActitvity(){
    override fun getLayoutId(): Int {
       return R.layout.activity_setting
    }

    override fun getTitleText(): String {
       return getString(com.example.lib_base.R.string.app_title_system_setting)
    }

    override fun isShowBack(): Boolean {
       return true
    }

    override fun initView() {

    }
}