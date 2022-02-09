package io.mercedesbenz.screens

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import io.mercedesbenz.TestContext
import io.mercedesbenz.util.OS
import org.openqa.selenium.support.PageFactory

abstract class Screen(testContext: TestContext) {
    val os = if (testContext.driver is AndroidDriver) OS.ANDROID else OS.IOS

    init {
        PageFactory.initElements(AppiumFieldDecorator(testContext.driver), this)
    }
}