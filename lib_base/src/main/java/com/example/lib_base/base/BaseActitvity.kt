package com.example.lib_base.base

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lib_base.utils.L
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission

/**
 * 文件名: BaseActitvity
 * 作者: 13967
 * 时间: 2021/2/7 16:10
 * 描述:
 */
abstract class BaseActitvity : AppCompatActivity() {
    protected val CODE_WINDOW_PERMISSION = 1000

    //获取布局
    abstract fun getLayoutId(): Int

    //获取标题
    abstract fun getTitleText(): String

    //是否显示返回键
    abstract fun isShowBack(): Boolean

    //初始化
    abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        //当前版本大于等于
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportActionBar?.let {
                it.title = getTitleText()
                it.setDisplayHomeAsUpEnabled(isShowBack())//显示返回键
                it.elevation = 0f//透明值
            }
        }
        initView()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    //检查窗口权限
    protected fun checkWindowPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this)
        }
        return true
    }

    //申请权限
    protected fun requestwindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startActivityForResult(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")
                ), CODE_WINDOW_PERMISSION
            )

        }
    }

    //检查权限
    protected fun chalkPermission(permission: String): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //当前版本要大于等于23版本
          //  return   checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED
            return   checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    //请求权限
    protected fun requestpermission(permission: Array<String>, granted: Action<List<String>>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndPermission.with(this)
                .runtime()
                .permission(permission)
                .onGranted(granted)
                .start()
        }
    }

}
