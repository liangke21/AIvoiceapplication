package com.example.aivoikeapp

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.aivoikeapp.data.MainListData
import com.example.aivoikeapp.service.VoiceService
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.base.adapter.BasepagerAdapter
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_base.utils.L
import com.yanzhenjie.permission.Action
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActitvity() {

    private var mList = ArrayList<MainListData>()

    private var mListView = ArrayList<View>()


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

        //申请权限

        if (chalkPermission(android.Manifest.permission.RECORD_AUDIO)) {
            L.e("有权限")
            linkService()
        } else {
            requestpermission(
                arrayOf(android.Manifest.permission.RECORD_AUDIO)
            ) {
                L.e("没权限11")
                linkService()
            }
        }
        //窗口权限
        if (!checkWindowPermission()) {
            requestwindowPermission(packageName)
        }


//设置ViewPager
        initPagerData()
//ViewPager数据参数
        initPagerView()
    }

    //设置ViewPager
    private fun initPagerView() {
        mViewPager.pageMargin = 20 //页边距
        mViewPager.offscreenPageLimit = mList.size //缓存多少个页面
        mViewPager.adapter = BasepagerAdapter(mListView)
    }

    //初始化数据
    private fun initPagerData() {
        //获取配置
        val title = resources.getStringArray(R.array.MainTitleArray)//标题
        val color = resources.getIntArray(R.array.MainColorArray)//颜色
        val icon = resources.obtainTypedArray(R.array.MainIconArray)//图标
        //第一个参数 指数 ,第二个 参数值
        for ((index, value) in title.withIndex()) {//将配置添加进集合
            mList.add(MainListData(value, icon.getResourceId(index, 0), color[index]))
        }
        val windowHewight = windowManager.defaultDisplay.height//获取当前屏幕的高度
        mList.forEach {
            //获取布局文件
            val view = View.inflate(this, R.layout.layout_main_list, null)
            //获取布局文件里的控件
            val mCvMainView = view.findViewById<CardView>(R.id.mCvMainView)
            val mIvMainIcon = view.findViewById<ImageView>(R.id.mIvMainIcon)
            val mTvMainText = view.findViewById<TextView>(R.id.mTvMainText)
            //设置参数
            mCvMainView.setCardBackgroundColor(it.color)
            mIvMainIcon.setImageResource(it.icon)
            mTvMainText.text = it.title

            mCvMainView.layoutParams?.let { Ip ->  //设置高度
                Ip.height = windowHewight / 5 * 3 //5分支3的高度

            }

            view.setOnClickListener { _ ->

                when (it.icon) {
                    R.drawable.img_main_weather
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)
                    R.drawable.img_mian_contell
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
                    R.drawable.img_main_joke_icon
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
                    R.drawable.img_main_map_icon
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
                    R.drawable.img_main_app_manager
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
                    R.drawable.img_main_voice_setting
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_VOVCE_SETTING)
                    R.drawable.img_main_system_setting
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
                    R.drawable.img_main_developer
                    -> ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPER)
                }
            }

            //将这个布局添加进集合
            mListView.add(view)
        }

    }

    /* //申请权限
     private fun requestPermissions() {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //当前版本要大于等于23版本

             if (checkSelfPermission(android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) { //判断有没有这个权限权限
                 linkService()
             } else {
                 AndPermission.with(this)
                     .runtime()
                     .permission(Permission.RECORD_AUDIO)
                     .onGranted { linkService() }
                     .start()
             }

         } else {
             linkService()
         }

     }*/

    //连接服务
    private fun linkService() {
        startService(Intent(this, VoiceService::class.java))
    }

}

