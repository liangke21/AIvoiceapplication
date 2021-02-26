//引用插件
plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}
//Android属性
android {
    compileSdkVersion (AppConfig.compileSdkVersion)
    buildToolsVersion (AppConfig.buildToolsVersion)

    defaultConfig {

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

    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //retrofit2网络矿建
    api(Dependencies.RETROFIT)
    api(Dependencies.RETROFIT_GSON)


}