import java.net.URI

object ProjectConfig{
    const val compose_version = "1.0.5"
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 28
}

/**
 * 模拟 gradle BuildConfig 文件里的自定义字段定义。
 *
 * [type]           字段类型，例如：int、String、boolean
 * [fieldName]      字段名称
 * [fieldValue]     字段值
 */
data class GradleBuildConfigField(
    val type:String,
    val fieldName:String,
    val fieldValue:String
)

object RtcSdkCommonConfig{
    val releaseBuildConfigFields = listOf(
        // 版本信息 ------------------------------
        GradleBuildConfigField("int","SDK_VERSION_CODE","${VersionConfig.versionCode}"),
        GradleBuildConfigField("String","SDK_VERSION_NAME", "\"${VersionConfig.versionName}\""),
        // Log 开关控制 ------------------------------
        GradleBuildConfigField("boolean","IS_SHOW_LOG", "true"),
        GradleBuildConfigField("boolean","IS_LOG_TO_FILE", "true"),
        // LogUtils 全局日志标签
        GradleBuildConfigField("String","GLOBAL_LOG_TAG", "\"--- RTC Engine ---\""),
        // 达达引擎内部常量配置 ------------------------------
        // 达达 SDK 内部服务启动超时配置，服务在超时时间后仍未启动则表示服务启动失败，这相当于 SDK 初始化失败
        GradleBuildConfigField("Long", "SERVICE_INIT_TIMEOUT", "6000L"),
        // 默认的 http 请求超时配置
        GradleBuildConfigField("Long", "DEFAULT_HTTP_TIMEOUT", "6000L"),
        // 默认的 IM 通讯超时配置
        GradleBuildConfigField("Long", "DEFAULT_IM_TIMEOUT", "6000L"),
        // 固定的电话号码
        GradleBuildConfigField("String", "FIX_PHONE_NUMBER", "\"13800000000\""),
        // 等待对方加入 Agora 房间的超时时间
        GradleBuildConfigField("Long", "OTHER_SIDE_JOIN_AGORA_CHANNEL_TIMEOUT", "100000L"),
        // 默认 clink 地址
        GradleBuildConfigField("String", "DEFAULT_BASE_URL", "\"https://api-bj-test14.clink.cn\""),
        // 状态栏通知 ID
        GradleBuildConfigField("String", "CHANNEL_ID", "\"call_channel\""),
        // 状态栏通知组名称
        GradleBuildConfigField("String", "NOTIFICATION_GROUP", "\"call_group\""),

        )
    val debugBuildConfigFields = listOf(
        // 版本信息 ------------------------------
        GradleBuildConfigField("int","SDK_VERSION_CODE","${VersionConfig.versionCode}"),
        GradleBuildConfigField("String","SDK_VERSION_NAME", "\"${VersionConfig.versionName}-debug\""),
        // Log 开关控制
        GradleBuildConfigField("boolean","IS_SHOW_LOG", "true"),
        GradleBuildConfigField("boolean","IS_LOG_TO_FILE", "true"),
        // LogUtils 全局日志标签
        GradleBuildConfigField("String","GLOBAL_LOG_TAG", "\"--- RTC Engine ---\""),
        // 达达引擎内部常量配置 ------------------------------
        // 达达 SDK 内部服务启动超时配置，服务在超时时间后仍未启动则表示服务启动失败，这相当于 SDK 初始化失败
        GradleBuildConfigField("Long", "SERVICE_INIT_TIMEOUT", "6000L"),
        // 默认的 http 请求超时配置
        GradleBuildConfigField("Long", "DEFAULT_HTTP_TIMEOUT", "6000L"),
        // 默认的 IM 通讯超时配置
        GradleBuildConfigField("Long", "DEFAULT_IM_TIMEOUT", "6000L"),
        // 固定的电话号码
        GradleBuildConfigField("String", "FIX_PHONE_NUMBER", "\"13800000000\""),
        // 等待对方加入 Agora 房间的超时时间
        GradleBuildConfigField("Long", "OTHER_SIDE_JOIN_AGORA_CHANNEL_TIMEOUT", "100000L"),
        // 默认 clink 地址
        GradleBuildConfigField("String", "DEFAULT_BASE_URL", "\"https://api-bj-test14.clink.cn\""),
        // 状态栏通知 ID
        GradleBuildConfigField("String", "CHANNEL_ID", "\"call_channel\""),
        // 状态栏通知组名称
        GradleBuildConfigField("String", "NOTIFICATION_GROUP", "\"call_group\""),

        )

    const val groupId="com.tinet.rtc_sdk"
    const val artifactId = "rtc-sdk"
    const val version = VersionConfig.versionName
}

object RtcSdkCustomerConfig{
    const val groupId="com.tinet.rtc_sdk"
    const val artifactId = "rtc-sdk-customer"
    const val version = VersionConfig.versionName
}

object RtcSdkMerchantConfig{
    const val groupId="com.tinet.rtc_sdk"
    const val artifactId = "rtc-sdk-merchant"
    const val version = VersionConfig.versionName
}

/**
 * 存放通用的 maven 仓库配置
 *
 * [name]                       仓库名称
 * [url]                        仓库 URL
 * [username]                   登录仓库的用户名
 * [password]                   登录仓库的密码
 * [isAllowInsecureProtocol]    配置是否使用类似 http 的不安全协议，true 使用，false 反之，默认为 true
 */
data class MavenRepository(
    val name:String,
    val url: URI,
    val username:String,
    val password:String,
    val isAllowInsecureProtocol:Boolean = true
)

object MavenRepositoryConfig{
    val mavenRepositories = listOf(
        MavenRepository(
            "Release",
            URI("http://49.233.20.130:8081/repository/maven-release/"),
            "jiangwei",
            "Mtnmtnm02M"
        ),
        MavenRepository(
            "Snapshot",
            URI("http://49.233.20.130:8081/repository/maven-snapshots/"),
            "jiangwei",
            "Mtnmtnm02M"
        )
    )
}

object VersionConfig{
    const val versionCode = 1
    const val versionName = "1.0.0"
}


object RtcAppConfig{

}


object RtcAppJavaConfig{

}