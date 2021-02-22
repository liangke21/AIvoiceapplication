package com.example.module_constellation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_CONSTELLATION)
class ConstellationActivity :BaseActitvity() {
    override fun getLayoutId(): Int {
     return R.layout.activity_constellation
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_constellation)
    }

    override fun isShowBack(): Boolean {
       return true
    }

    override fun initView() {

    }
}