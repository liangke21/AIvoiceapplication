//引用插件
plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
}
//Android属性
android {
    compileSdkVersion (AppConfig.compileSdkVersion)
    buildToolsVersion (AppConfig.buildToolsVersion)

    defaultConfig {
///不要包名
        minSdkVersion (AppConfig.minSdkVersion)
        targetSdkVersion (AppConfig.targetSdkVersion )
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        consumerProguardFiles ("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //kotlin基础库
    api(Dependencies.APP_COMPAT)
    //Android标准库
    api(Dependencies.Ktx_CORE)
    //kotlin核心库
    api(Dependencies.STD_LID)
    //EventBus
    api(Dependencies.EVENT_BUS)
    //ARouter
    api(Dependencies.AROUTER)
    //Recyclerview
    api(Dependencies.RECYCLERVIEW)
    //AndPermission请求权限
    api(Dependencies.ANDPERMISSION)
    //viewpager
    api(Dependencies.VIEWPAGER)
    //谷歌材料库
    api(Dependencies.MATERIAL)

    api(project(":lib_network"))
    api(project(":lib_voice"))

     //创建组件自带库
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    //app 自带库
    implementation ("com.google.android.material:material:1.2.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

}