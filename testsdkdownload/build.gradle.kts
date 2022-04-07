plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
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

    task<Exec>(name = "downLoadSdk") {
        workingDir = File("./")

        commandLine = listOf(
            "python",
            "download_native_sdk.py",
            "--sdk_download_url",
            "https://artifact-master.zego.cloud/generic/rtc/public/native/ZegoExpressVideo/android/ZegoExpressVideo-android-shared-java.zip?version=2.17.1.20322",
            "--sdk_zip_root_folder",
            "release/Library"
        )
    }

    tasks.withType(JavaCompile::class.java) {
        dependsOn("downLoadSdk")
    }
}

afterEvaluate {
    publishing{
        publications{
            create("_TestSdkDownload-release_",MavenPublication::class.java){
                groupId="io.github.JarvisSemou"
                artifactId="TiAndroidTools"
                version = "v1.0.0"
                from(components["release"])
            }

            create("_TiAndroidTools-debug_",MavenPublication::class.java){
                groupId="io.github.JarvisSemou"
                artifactId="TiAndroidTools"
                version = "v1.0.0-SNAPSHOT"
                from(components["debug"])
            }
        }
    }
}

dependencies {

//    implementation("androidx.core:core-ktx:1.7.0")
//    implementation("androidx.appcompat:appcompat:1.4.1")
//    implementation("com.google.android.material:material:1.5.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    api(
        fileTree(
            mapOf(
                "include" to "*.jar",
                "dir" to "libs"
            )
        )
    )
}