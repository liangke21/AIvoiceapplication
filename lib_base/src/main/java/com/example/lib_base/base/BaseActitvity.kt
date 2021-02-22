package com.example.lib_base.base

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * 文件名: BaseActitvity
 * 作者: 13967
 * 时间: 2021/2/7 16:10
 * 描述:
 */
abstract class BaseActitvity:AppCompatActivity() {

    //获取布局
    abstract fun getLayoutId():Int

    //获取标题
    abstract fun getTitleText():String
    //是否显示返回键
    abstract fun isShowBack():Boolean

    //初始化
    abstract fun initView()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    //当前版本大于等于
if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
   supportActionBar?.let {
        it.title = getTitleText()
        it.setDisplayHomeAsUpEnabled(isShowBack())//显示返回键
        it.elevation = 0f//透明值
    }
}
  initView()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
           finish()
        }
        return true
    }




}
