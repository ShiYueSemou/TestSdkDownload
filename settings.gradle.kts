pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{url=uri("https://jitpack.io")}
//        flatDir {
//            dirs = setOf(
//                File("./testsdkdownload/libs")
//            )
//        }
    }
}
rootProject.name = "TestSdkDownload"
include(":app")
include(":testsdkdownload")
include(":test_download_app")
include(":showversion")
include(":TestSdkDownload2")
