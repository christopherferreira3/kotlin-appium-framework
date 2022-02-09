# Kotlin-Appium-Framework

## Technologies
- Allure reports
- Junit 5
- Maven
- Appium

## Pre-requisites
- Android SDK and tools (with the correct paths in your shell profile)
- Appium installed locally (`npm -g install appium`)

## Running
1. Create an emulator in Android Studio
2. Install the app on the Emulator (Android) 
   `adb install APP.apk`
3. Start the Appium server `appium`
4. Run the tests `mvn test -Ddevices=EMULATOR_NAME -Dos=Android -Dversion=10.0`

## Available arguments
### Devices
The list of devices where you want to run the tests on. Basic logic for this is that 
you would want to run your tests on multiple devices of the same version. For now, you 
can only run a single Android version to multiple devices, the extra mapping is not 
being considered for now.

### OS (Operative System)
Used to differentiate the tests  between iOS and Android. Some apps are different 
depending on the operative system so leaving this open for now.

### Version
Specific the Android version for the tests to run. Using this as a list just 
like "Devices" is still being considered.


### Current status
- [x] Android
- [ ] iOS


### TODO
1. Install directly the APK instead of having it installed before
2. Support iOS
3. Direct tests to SauceLabs (may need a tunnel for ADB install unless it supports direct installation from 
   capabilities)
4. Support dual locators to enable locating by Android locator and iOS locator. For now, only generic FindBy's work.