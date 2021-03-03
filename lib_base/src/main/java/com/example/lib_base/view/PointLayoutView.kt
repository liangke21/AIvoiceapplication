package com.example.lib_base.view

import android.app.ActionBar
import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.lib_base.R

/**
 * 作者: QQ:1396797522
 * 时间: 2021/3/3 15:19
 * 描述:  小白点布局view
 */
class PointLayoutView : LinearLayout {
    private val mList = ArrayList<ImageView>()

    constructor(context: Context?) : super(context) {
        initLayout()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initLayout()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLayout()
    } //快捷键alt+ins

    //初始化
    private fun initLayout() {
        orientation = HORIZONTAL//方向是水平方向
        gravity = Gravity.CENTER //居中
    }

    //设置页面数量
    fun setPointSize(size: Int) {
        if (mList.size > 0) {
            mList.clear()//清空
        }
        for (i in 0 until size) {
            val iv = ImageView(context)
            addView(iv)//添加到容器里面
            mList.add(iv)
        }
        //默认第一页
        setCheck(0)
    }

    //设置选择
    fun setCheck(index: Int) {
        if (index > mList.size) {
            return
        }
        mList.forEachIndexed { i, imageView ->
            imageView.setImageResource(
                if (i == index) R.drawable.img_app_manager_point_p else
                    R.drawable.img_app_manager_point
            )
        }
    }

}