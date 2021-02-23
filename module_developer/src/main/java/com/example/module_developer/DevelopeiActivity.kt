package com.example.module_developer


import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.base.adapter.CommonAdapter
import com.example.lib_base.base.adapter.CommonViewHolder
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_base.utils.L
import com.example.lib_voice.TTs.VoiceTTs
import com.example.lib_voice.manager.VoiceManager
import com.example.lib_voice.xunfeiTTS.xunfeiTTs
import com.example.module_developer.data.DeveloperListData
import kotlinx.android.synthetic.main.activity_developei.*

/*
* 开发者模式*/
@Route(path = ARouterHelper.PATH_DEVELOPER)
class DevelopeiActivity : BaseActitvity() {
    //标题
    private val mTypeTitle = 0

    //内容
    private val mTypeContent = 1

    private val mList = ArrayList<DeveloperListData>()



    override fun getLayoutId(): Int {
        return R.layout.activity_developei
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_developer)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
        initData()
        initListView()

    }

    //初始化数据
    private fun initData() {
        //拿到写死的数组
        val dataArray = resources.getStringArray(com.example.lib_base.R.array.DeveloperListArray)
        dataArray.forEach { //遍历一下
            //是标题
            if (it.contains("[")) {//包含有[
                addTtemData(mTypeTitle, it.replace("[", "").replace("]", ""))//[替换成空字符
            } else {
                addTtemData(mTypeContent, it)
            }
        }
    }

    //初始化列表
    private fun initListView() {
        //布局管理器
        rvDeveloperView.layoutManager = LinearLayoutManager(this)
        //分割线
        rvDeveloperView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        //万能适配器
        rvDeveloperView.adapter =
            CommonAdapter(mList, object : CommonAdapter.OnBMoreindDataListener<DeveloperListData> {
                override fun onBindViewHolder(
                    model: DeveloperListData,
                    viewHolder: CommonViewHolder,
                    type: Int,
                    position: Int
                ) {
                    when (model.type) {
                        mTypeTitle -> {
                            viewHolder.setText(R.id.mTvDeveloperTitle, model.text)
                        }
                        mTypeContent -> {
                            viewHolder.setText(
                                R.id.mTvDeveloperContent,
                                "${position}.${model.text}"
                            )
                            viewHolder.itemView.setOnClickListener {
                                itemClickFun(position)
                            }
                        }
                    }
                }

                override fun getLayoutId(type: Int): Int {
                    return if (type == mTypeTitle) {
                        R.layout.layout_developer_title
                    } else {
                        R.layout.layout_developer_content
                    }
                }

                override fun getItemViewType(position: Int): Int {
                    return mList[position].type
                }
            })

    }

    //添加数据
    private fun addTtemData(type: Int, text: String) {
        mList.add(DeveloperListData(type, text))
    }

    //点击事件
    private fun itemClickFun(position: Int) {
        when (position) {
            //跳转应用
            1 -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
            2 -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
            3 -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
            4 -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
            5 -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
            6 -> ARouterHelper.startActivity(ARouterHelper.PATH_VOVCE_SETTING)
            7 -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)
//TTs语音测试
            20 -> VoiceManager.start("我是百度语音默认普通女声")
            21 -> {
                VoiceManager.start("你好我是小杜", object : VoiceTTs.OnTTSResultListener {
                    override fun ttsEnd() {
                        L.i("VoiceTTs.OnTTSResultListener:我是播放结束的回调")
                    }
                })

                Toast.makeText(this, "VoiceTTs.OnTTSResultListener:我是播放结束的回调", Toast.LENGTH_SHORT).show()

            }
            22 -> VoiceManager.pause()
            23 -> VoiceManager.resume()
            24-> VoiceManager.stop()
            25 -> VoiceManager.release()
//讯飞语音测试
            27 -> xunfeiTTs.start("我是讯飞语音一菲小姐姐,比百度语音的声音好听")
            28 -> xunfeiTTs.pause()
            29 -> xunfeiTTs.resume()
            30-> xunfeiTTs.stop()

        }
    }



}