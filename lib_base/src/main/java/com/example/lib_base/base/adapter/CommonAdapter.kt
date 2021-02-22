package com.example.lib_base.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
/**
 * 作者: 13967
 * 时间: 2021/2/18 16:40
 * 描述: 万能适配器RecyclerView
 */
class CommonAdapter<T>:RecyclerView.Adapter<CommonViewHolder> {

    //数据
    private  var mList: List<T>

    //接口
    private  var onBindDataListener:OnBindDataListener<T>? = null
    private  var onMorBindDataListener:OnBMoreindDataListener<T>? = null
    constructor(mList: List<T>,onBindDataListener:OnBindDataListener<T>){//多个构造参数
        this.mList=mList
        this.onBindDataListener=onBindDataListener
    }


    constructor(mList: List<T>,onMorBindDataListener:OnBMoreindDataListener<T>){//多Type构造参数
        this.mList=mList
        this.onBindDataListener = onMorBindDataListener
        this.onMorBindDataListener=onMorBindDataListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {//点击事件
       val layoutId=onBindDataListener?.getLayoutId(viewType)

        return CommonViewHolder.getViewHolder(parent,layoutId!!)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {//显示的什么东西
        ///mList[position]当前角标的值
       onBindDataListener?.onBindViewHolder(mList[position],holder,getItemViewType(position),position)
    }

    override fun getItemCount(): Int {//显示个数
       return  mList.size
    }

    override fun getItemViewType(position: Int): Int {

        if (onMorBindDataListener != null){
            return onMorBindDataListener!!.getItemViewType(position)
        }
return  0
    }


    interface OnBindDataListener<T>{//标准的接口
       fun onBindViewHolder(model:T,viewHolder: CommonViewHolder,type:Int,position: Int)
       fun getLayoutId(type: Int):Int
    }

    interface OnBMoreindDataListener<T>:OnBindDataListener<T> {//多Type
        fun getItemViewType(position: Int): Int
    }
}
