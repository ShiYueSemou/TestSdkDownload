import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    // 文件下载插件
    id("de.undercouch.download")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    val version = "1.0.0"
    val sdkName = "TPhoneSDKCore.zip"
    val downloadedSdkFile = File("$buildDir/ti-sdk/$sdkName")

    task<de.undercouch.gradle.tasks.download.Download>("downloadSdk") {
        group = "custom"
        src("https://tinet-sdk-release.s3.cn-north-1.amazonaws.com.cn/tphone/sdk_core/android/v${version}/TPhoneSDKCore.zip")
        dest(downloadedSdkFile)
        overwrite(true)
        onlyIfModified(true)
        doLast {
            println("start to copy libs")

            copy {
                from(zipTree(downloadedSdkFile))
                include("arm*/**","x86*/**")
                into("${project.projectDir}/src/main/jniLibs")
            }

            copy{
                from(zipTree(downloadedSdkFile))
                include("*.jar")
                into("${project.projectDir}/libs")
            }
        }

    }

    tasks.named("preBuild"){
        dependsOn("downloadSdk")
    }

}

afterEvaluate {
    publishing {
        publications {
            create("_TestSdkDownload-release_", MavenPublication::class.java) {
                groupId = "com.github.ShiYueSemou"
                artifactId = "TestSdkDownload"
                version = "v1.0.0"
                from(components["release"])
            }
        }
    }
}

dependencies {
    api(
        fileTree(
            mapOf(
                "include" to "*.jar",
                "dir" to "libs"
            )
        )
    )
}