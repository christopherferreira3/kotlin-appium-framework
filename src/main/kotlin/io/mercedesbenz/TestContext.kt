package io.mercedesbenz

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

class TestContext(
        driver: Lazy<AppiumDriver<MobileElement>>
) {
    val driver by driver
}