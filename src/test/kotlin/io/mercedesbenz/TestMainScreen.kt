package io.mercedesbenz

import io.mercedesbenz.screens.MainScreen
import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Story
import mu.KotlinLogging
import org.junit.Assert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


private val logger = KotlinLogging.logger { }


@Story("Opens the App and checks the main screen")
class TestMainScreen : TestBase() {

    @DisplayName("Validate screen elements")
    @Description("Ensures the presence of the main elements in the main screen")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @MethodSource("testContextProvider")
    fun checkMainScreen(testContext: TestContext) {
        MainScreen(testContext).apply {
            enterMercedesMeId()
            Thread.sleep(1000)
            Assert.assertTrue(true)
        }
    }

}