package com.example.lib_base.base.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_base.utils.L

/**
 * 作者: 13967
 * 时间: 2021/2/18 16:41
 * 描述:辅助我万能适配器的ViewHolder
 */
class CommonViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    //view 复用
 private var mViews:SparseArray<View?> = SparseArray()

    companion object{
        //获取Holder对象
        fun getViewHolder(parent:ViewGroup,layoutId:Int):CommonViewHolder{
      val itemView: View =  LayoutInflater.from(parent.context).inflate(layoutId,parent,false)
            return CommonViewHolder(itemView)
        }
    }

    //获取视图对象
    fun getView(viewId:Int):View{
    //每次获取看能不能拿到
   var view:View?  =  mViews[viewId]//viewId为键

        if (view == null){

           view = itemView.findViewById(viewId)//获取布局id
            mViews.put(viewId,view) //把键和值保存起来
        }
       return view!!
    }

    fun setText(viewId: Int,text:String){//设置文本
        (getView(viewId) as TextView).text=text

    }

    fun setImage(viewId: Int,resId:Int){//设置图片

        (getView(viewId) as ImageView).setImageResource(resId)

    }

}
