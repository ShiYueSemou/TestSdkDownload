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
