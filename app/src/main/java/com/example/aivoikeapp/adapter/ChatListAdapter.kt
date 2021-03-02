package com.example.aivoikeapp.adapter

import com.example.aivoikeapp.R
import com.example.aivoikeapp.data.ChatLis
import com.example.aivoikeapp.entity.AppConstanst
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


    override fun onBindViewHolder(
        model: ChatLis,
        viewHolder: CommonViewHolder,
        type: Int,
        position: Int
    ) {
        when (type) {
            AppConstanst.TYPE_MINE_TEXT -> viewHolder.setText(R.id.tv_main_text, model.text)
            AppConstanst.TYPE_AI_TEXT -> viewHolder.setText(R.id.tv_ai_text, model.text)
            AppConstanst.TYPE_AI_WEATHER -> {

            }
        }


    }

    override fun getLayoutId(type: Int): Int {
        return when (type) {
            AppConstanst.TYPE_MINE_TEXT -> R.layout.layout_main_text
            AppConstanst.TYPE_AI_TEXT -> R.layout.layout_ai_text
            AppConstanst.TYPE_AI_WEATHER -> R.layout.layout_ai_weather
            else -> 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }
})



