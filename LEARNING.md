
1.核心思想 (技术)

Kotlin + 组件化 + Kotlin Gradle DsL

# Kotlin Gradle DsL 是什么

传统Gradle - 是Groovy语言编写的

Kotlin Gradle - 是 Kotlin编写的
https://docs.gradle.org/current/userguide/kotlin_dsl.html

buildSrc构建
build.gradle.kts//

plugins {
    `kotlin-dsl`
}
repositories{
    jcenter()
}

##组件化app
远古应用:App(一堆代码)

##构建组件化App

App
Module 笑话 地图 星座 语音设置 系统设置 天气 应用管理 开发者模式
lib lib_base lib_network lib_voice 
##EventBus
https://github.com/greenrobot/EventBus/
##ARouter
https://github.com/alibaba/ARouter

##5.服务保活
- 1.系统自带,系统做了一些友好的保活 - FLAG
  /**
     *START_STICKY:当系统内存不足的时候,杀掉服务,那么在系统紧张的时候才启动服务
     * START_NOT_STICKY: 当系统内存不足的时候,杀掉了服务,直达下一次startServicece才启动
     * START_REDELIVER_INTENT: 重新传递Intent值
     * START_STICKY_COMPATIBILITY: START_STICKY兼容版本,但是它也不能保证系统killd掉服务一定能重启
     */
     
 - 5.前台服务  
 - 我在前台运行,我绑定通知栏,在服务中创建通知栏  
     