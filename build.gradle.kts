// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

   val kotlin_version by extra("1.4.30")

//扩展类  编译配置

repositories {//仓库的配置
    google()
    jcenter()
    maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }

}
dependencies {//依赖配置
    classpath ("com.android.tools.build:gradle:${KotlinConstants.gradle_version}")
    classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinConstants.kotlin_version}")

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle.kts.kts.kts.kts.kts.kts.kts.kts.kts.kts.kts files
}
}

plugins {
id("de.fayard.buildSrcVersions") version "0.3.2"
}


allprojects {//应用配置
repositories {
    google()
    jcenter()

    maven { setUrl("https://jitpack.io") }
    //阿里云
    maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }
    maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/jcenter") }
    maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/google") }
    maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/gradle-plugin") }



}
}
tasks{ //应用任务
val clean by registering(Delete::class)
delete(buildDir)
}
