package com.example.module_voice_setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActitvity
import com.example.lib_base.base.adapter.CommonAdapter
import com.example.lib_base.base.adapter.CommonViewHolder
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_voice.manager.VoiceManager
import kotlinx.android.synthetic.main.activity_voice_setting.*

@Route(path = ARouterHelper.PATH_VOVCE_SETTING)
class VoicesettingActivity : BaseActitvity() {


    private val mList: ArrayList<String> = ArrayList()

    private var mTtsPeopleIndex: Array<String>? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_voice_setting
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_voice_setting)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
        //默认值
        bar_voice_cpeed.progress = 5
        bar_voice_volume.progress = 5

        //最大值
        bar_voice_cpeed.max = 15
        bar_voice_volume.max = 15

        initData()

        initListener()  //监听语速和音量

        initPeopleView()

        btn.setOnClickListener {
            VoiceManager.TTstart("大家好我是百度测试语音")//播放测试
        }

    }

    //初始化数据
    private fun initData() {
        val mTtsPeople = resources.getStringArray(R.array.TTSPeople)//获取发音人
        mTtsPeopleIndex = resources.getStringArray(R.array.TTSPeopleIndex)//获取发音人对应参数

        mTtsPeople.forEach {
            mList.add(it)
        }

    }

    private fun initPeopleView() {
        rv_voice_peope.layoutManager = LinearLayoutManager(this)
        rv_voice_peope.addItemDecoration(
            DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL
            )
        )//添加分割线
        rv_voice_peope.adapter =
            CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<String> {
                override fun onBindViewHolder(
                    model: String,
                    viewHolder: CommonViewHolder,
                    type: Int,
                    position: Int
                ) {

                    viewHolder.setText(R.id.mTvpopeleContent, model)
                    viewHolder.itemView.setOnClickListener {
                        mTtsPeopleIndex?.let {
                            VoiceManager.setVicepeople(it[position])
                        }

                    }

                }

                override fun getLayoutId(type: Int): Int {

                    return R.layout.layout_tts_people_list
                }
            })
    }

    private fun initListener() {
        //监听
        bar_voice_cpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                bar_voice_cpeed.progress = progress
                //设置语速
                VoiceManager.setVicecpeed(progress.toString())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //开始按到小圆点
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //停止按到小圆点
            }
        })


        //监听
        bar_voice_volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                bar_voice_volume.progress = progress
                //设置语速
                VoiceManager.setViceVolume(progress.toString())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //开始按到小圆点
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //停止按到小圆点
            }
        })

    }


}