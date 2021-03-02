package com.example.aivoikeapp.adapter

import com.example.aivoikeapp.R
import com.example.aivoikeapp.data.ChatLis
import com.example.lib_base.base.adapter.CommonAdapter
import com.example.lib_base.base.adapter.CommonViewHolder

/**
 * 作者: QQ:1396797522
 * 时间: 2021/3/2 13:53
 * 描述: 对话列表适配器
 */
class ChatListAdapter(
    mList: List<ChatLis>

) : CommonAdapter<ChatLis>(mList, object : OnBMoreindDataListener<ChatLis> {
    //会话类型
    private val typeMineText = 0
    private val typeAiText = 1
    private val typeAiWeather = 2

    override fun onBindViewHolder(
        model: ChatLis,
        viewHolder: CommonViewHolder,
        type: Int,
        position: Int
    ) {

    }

    override fun getLayoutId(type: Int): Int {
        return when (type) {
            typeMineText -> R.layout.layout_main_text
            typeAiText -> R.layout.layout_ai_text
            typeAiWeather -> R.layout.layout_ai_weather
            else -> 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }
}) {


}
