package com.example.module_app_manager

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.helper.ARouterHelper

/**
 * 文件名: AppManagerActivity
 * 作者: 13967
 * 时间: 2021/2/7 16:46
 * 描述:
 */
@Route(path = ARouterHelper.PATH_APP_MANAGER)
class AppManagerActivity:BaseActitvity() {
    override fun getLayoutId(): Int {
       return R.layout.activity_app_manager
    }

    override fun getTitleText(): String {
       return getString(com.example.lib_base.R.string.app_title_app_manager)
    }

    override fun isShowBack(): Boolean {
      return true
    }

    override fun initView() {

    }
}
