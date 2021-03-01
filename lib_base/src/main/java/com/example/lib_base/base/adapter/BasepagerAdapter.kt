package com.example.lib_base.base.adapter

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * 作者: QQ:1396797522
 * 时间: 2021/3/1 14:48
 * 描述:
 */
class BasepagerAdapter(private val mList:List<View>):PagerAdapter() {
    override fun getCount(): Int {
        return mList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

   //实例化项目
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        (container as ViewPager).addView(mList[position])

        return mList[position]
    }
   //销毁项目
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       (container as ViewPager).removeView(mList[position])
    }
}
