package com.example.aivoikeapp.data


/**
 * 文件名: KtData
 * 作者: 13967
 * 时间: 2021/3/1 14:34
 * 描述:  Kotlin Data 数据
 */

data class MainListData(
    val title: String,
    val icon: Int,
    val color: Int,
) {

}

data class ChatLis(
    val type: Int,//回话类型

){
   lateinit var  text: String //文本
}