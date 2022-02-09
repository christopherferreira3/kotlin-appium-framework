package io.mercedesbenz

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import mu.KotlinLogging
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.*

private val logger = KotlinLogging.logger { }

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SeleniumTestWatcher::class)
abstract class TestBase {

    lateinit var name: String
    lateinit var testClass: String

    companion object {
        private const val TEST_CONFIG_PROPERTIES = "config.properties"
        const val APPIUM_URL = "appiumURL"
        const val APK_PACKAGE = "apkPackage"
        const val DEVICE = "devices"
        const val MAIN_ACTIVITY = "appActivity"
        const val VERSION = "version"  // Android version for instance
        const val OS = "os"  // Android or iOS
    }

    val properties by lazy {
        this::class.java.classLoader
            .getResourceAsStream(TEST_CONFIG_PROPERTIES)!!.use {
                Properties(System.getProperties()).apply { load(it) }
            }
    }

    val devices: List<String> by lazy { properties.getProperty(DEVICE).split(",") }
    val platformVersion: String by lazy { properties.getProperty(VERSION)}
    val system: String by lazy { properties.getProperty(OS)}
    val appiumUrl: String by lazy { properties.getProperty(APPIUM_URL) }
    val packageName: String by lazy { properties.getProperty(APK_PACKAGE) }
    val appActivity: String by lazy { properties.getProperty(MAIN_ACTIVITY) }

    @BeforeAll
    fun setUp(testInfo: TestInfo) {
        testClass = testInfo.testClass.get().simpleName
    }

    @BeforeEach
    fun init(testInfo: TestInfo) {
        name = testInfo.testMethod.get().name
    }

    private fun buildMobileDriver(device : String) = lazy<AppiumDriver<MobileElement>> {
        val uuid = UUID.randomUUID()

        val cap =  DesiredCapabilities().apply {
            setCapability(
                "appPackage",
                packageName
            )
            setCapability(
                "appActivity",
                appActivity
            )
            setCapability(
                "platformName",
                system.capitalize()
            )
            setCapability(
                "platformVersion",
                platformVersion
            )
            setCapability(
                "deviceName",
                device
            )
        }
        logger.info { "Mobile Capabilities: $cap" }
        logger.info { "TEST ID: $uuid" }
        logger.info { "GRID: $appiumUrl" }
        logger.info { "DEVICE: $device" }
        logger.info { "Main Activity: $appActivity" }
        logger.info { "App Package: $packageName" }
        logger.info { "PlatformName: $system" }
        logger.info { "PlatformVersion: $platformVersion" }
        logger.info { "DEVICE: $device" }

        // Build Driver
        if (system == "Android") {
            AndroidDriver(URL(appiumUrl), cap)
        } else {
            IOSDriver(URL(appiumUrl), cap)
        }
    }

    fun testContextProvider() =
            devices.map { d ->
                TestContext(driver = buildMobileDriver(d))
            }

}
