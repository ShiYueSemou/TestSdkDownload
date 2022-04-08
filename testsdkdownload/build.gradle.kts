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

    task<de.undercouch.gradle.tasks.download.Download>("downLoadSdk") {
        src("https://tinet-sdk-release.s3.cn-north-1.amazonaws.com.cn/tphone/sdk_core/android/v1.0.0/TPhoneSDKCore.zip")
        dest(downloadedSdkFile)
        overwrite(true)
        onlyIfModified(true)
        doLast {
            println("start to copy libs")

            copy {
                from(zipTree(downloadedSdkFile))
                include("arm*/**")
                into("${project.projectDir}/src/main/jniLibs")
            }

            copy{
                from(zipTree(downloadedSdkFile))
                include("*.jar")
                into("${project.projectDir}/libs")
            }
        }

    }


    tasks.withType(JavaCompile::class.java) {
        dependsOn("downLoadSdk")
    }
}

afterEvaluate {
    publishing {
        publications {
            create("_TestSdkDownload-release_", MavenPublication::class.java) {
                groupId = "io.github.ShiYueSemou"
                artifactId = "TestSdkDownload"
                version = "v1.0.0"
                from(components["release"])
            }

            create("_TestSdkDownload-debug_", MavenPublication::class.java) {
                groupId = "io.github.ShiYueSemou"
                artifactId = "TestSdkDownload"
                version = "v1.0.0-SNAPSHOT"
                from(components["debug"])
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