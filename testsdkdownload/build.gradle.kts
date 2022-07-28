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
    val sdkFile = "xxx.zip"
    val downloadedSdkFile = File("$buildDir/downloaded_sdk/$sdkFile")

    task<de.undercouch.gradle.tasks.download.Download>("downloadSdk") {
        group = "custom"
        // your url
        src("https://your.url")
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
        publications.create<MavenPublication>("_TestSdkDownload-release_") {
            groupId = "com.github.ShiYueSemou"
            artifactId = "TestSdkDownload"
            version = "v1.0.0"
            from(components["release"])
        }
    }
}

dependencies {
    println("public ------> find and setting libs")
    File("${projectDir.path}/libs").list()?.forEach {
        println("public ------> use libs '$it'")
        implementation(files("libs/$it"))
    }
}