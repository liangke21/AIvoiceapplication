//引用插件
plugins {
    if(ModuleConfig.isApp){
        id ("com.android.application")
    }else{
        id ("com.android.library")
    }
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
    kotlin("kapt")
}
//Android属性
android {
    compileSdkVersion (AppConfig.compileSdkVersion)
    buildToolsVersion (AppConfig.buildToolsVersion)

    defaultConfig {
        if(ModuleConfig.isApp){
            applicationId = ModuleConfig.MODULE_JOKE
        }

        minSdkVersion (AppConfig.minSdkVersion)
        targetSdkVersion (AppConfig.targetSdkVersion )
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        consumerProguardFiles ("consumer-rules.pro")
        //ARouter软路由
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    //动态智源
    sourceSets{
        getByName("main"){
            if (ModuleConfig.isApp){
                manifest.srcFile("src/main/manifest/AndroidManifest.xml")
            }else{
                manifest.srcFile("src/main/AndroidManifest.xml")
            }
        }
    }


}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":lib_base"))
    //运行时注解
    kapt(Dependencies.AROUTER_COMPILER)

}