package com.test.news.utilities

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.test.news.basePage.BasePage
import com.test.news.features.login.presentation.LoginActivity

open class Utilities : BasePage() {

    fun enableWifiAndData() {
        sendShellCommand("svc wifi enable", "svc data enable")
        Thread.sleep(1000)
    }

    fun disableWifiAndData() {
        sendShellCommand("svc wifi disable", "svc data disable")
    }

    private fun sendShellCommand(wifiOption: String, dataOption: String) {
        val options = arrayOf(wifiOption, dataOption)
        for (option in options) {
            InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(option)
        }
    }

    fun appToBackgroundThenResume() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        device.pressRecentApps()
        val selector = UiSelector()
        val recentApp = device.findObject(selector.descriptionContains("NEWS"))
        Thread.sleep(2000)
        recentApp.click()
    }

    fun getCurrentPackage() : String {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        return device.currentPackageName
    }

    fun userIsNavigatedToChrome() {
        repeatFor { (getCurrentPackage() !== "com.test.news") }
        repeatFor { (getCurrentPackage() == "com.android.chrome") }
    }

    public fun launchActivity(newState: Lifecycle.State? = null) {
        var activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        newState?.let { activityScenario.moveToState(it) }
    }

}