package com.example.module_app_manager

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.base.adapter.BasepagerAdapter
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_base.helper.`fun`.AppHelper
import com.example.lib_base.helper.`fun`.AppHelper.mAllViewList
import com.example.lib_base.utils.L
import kotlinx.android.synthetic.main.activity_app_manager.*

/**
 * 文件名: AppManagerActivity
 * 作者: 13967
 * 时间: 2021/2/7 16:46
 * 描述:
 */
@Route(path = ARouterHelper.PATH_APP_MANAGER)
class AppManagerActivity : BaseActitvity() {

    private val waitApp = 1000

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == waitApp) {
                waitAPPHandler()
            }

        }
    }

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
        //   显示加载
        ll_loading.visibility = View.VISIBLE
        //说明初始化成功APPview
        waitAPPHandler()

    }

    //等待应用加载完成
    private fun waitAPPHandler() {
        L.i("等待APP列表刷新...")
        if (AppHelper.mAllViewList.size > 0) {
            initViewPage()
        } else {
            mHandler.sendEmptyMessageDelayed(waitApp, 1000)//发送延迟
        }
    }


    //初始化页面
    private fun initViewPage() {
        mViewPager.offscreenPageLimit = AppHelper.getPageSize()
        mViewPager.adapter = BasepagerAdapter(AppHelper.mAllViewList)
        ll_loading.visibility = View.GONE
        ll_content.visibility = View.VISIBLE
        mPointLayoutView.setPointSize(AppHelper.getPageSize())

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                mPointLayoutView.setCheck(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}
