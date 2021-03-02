



object KotlinConstants {
//根目录版本
    const  val gradle_version = "4.1.2"
    const  val kotlin_version = "1.4.21"//Kotlin 版本
}

object AppConfig{
    //依赖版本
    const val compileSdkVersion=30//依赖版本
    const val buildToolsVersion="30.0.3"//编译工具版本
    const val  applicationId = "com.example.aivoiceapp"//包名

    const val minSdkVersion =21//最小支持sdk
    const val   targetSdkVersion =30 //当前基于Sdk版本
    //版本编码
    const val versionCode = 1
    //版本名称
    const val  versionName = "1.0"
}
//核心库
object Dependencies{
   //kotlin基础库
    const val STD_LID="org.jetbrains.kotlin:kotlin-stdlib:${KotlinConstants.kotlin_version}"
    //Android标准库
    const val Ktx_CORE="androidx.core:core-ktx:1.3.2"
    //kotlin核心库
    const val APP_COMPAT="androidx.appcompat:appcompat:1.2.0"
    //EventBus
    const val  EVENT_BUS="org.greenrobot:eventbus:3.2.0"
    //ARouter软路由
    const val AROUTER = "com.alibaba:arouter-api:1.5.0"
    const val AROUTER_COMPILER = "com.alibaba:arouter-compiler:1.5.1"
    //Recyclerview
    const val RECYCLERVIEW= "androidx.recyclerview:recyclerview:1.2.0-alpha01"
    //请求权限
    const val  ANDPERMISSION ="com.yanzhenjie:permission:2.0.3"
   //retrofit2网络框架
    const val RETROFIT =  "com.squareup.retrofit2:retrofit:2.9.0"
    //retrofit2网络框架 json解析
    const val RETROFIT_GSON =  "com.squareup.retrofit2:converter-gson:2.8.1"
    //viewpager
    const val VIEWPAGER = "com.zhy:magic-viewpager:1.0.1"
   //谷歌材料库
    const val MATERIAL = "com.google.android.material:material:1.0.0"
    //lottie动画
    const val LOTTIE = "com.airbnb.android:lottie:3.4.0"

}

//Module配置
object ModuleConfig{
    //是否App
    const val isApp =false

    //包名
    const val MODULE_APP_MANAGER= "com.example.module_app_manager"
    const val MODULE_CONSTELLATION ="com.example.module_constellation"
    const val MODULE_DEVELOPER="com.example.module_developer"
    const val MODULE_JOKE="com.example.module_joke"
    const val MODULE_MAP="com.example.module_map"
    const val MODULE_SETTING="com.example.module_setting"
    const val MODULE_VOICE_SETTING="com.example.module_voice_setting"
    const val MODULE_WEATHER="com.example.module_weather"

}