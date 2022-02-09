package io.mercedesbenz.screens

import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import io.mercedesbenz.TestContext
import io.qameta.allure.Step
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class MainScreen(private val testContext: TestContext) : Screen(testContext){
    @AndroidFindBy(id = "com.daimler.ris.service.ece.android:id/input_edit_user")
    @iOSXCUITFindBy()
    private val emailInput: MobileElement? = null

    @Step("Checks for the presence of the E-mail input and fills it")
    fun enterMercedesMeId() {
        val emailField = WebDriverWait(testContext.driver, 10).until(
            ExpectedConditions.elementToBeClickable(emailInput).andThen {
                it.sendKeys(("test@user.com"))
            }
        )
    }
}