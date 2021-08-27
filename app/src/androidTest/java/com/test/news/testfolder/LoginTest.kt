package com.test.news.testfolder

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.robothelper.LoginScreenRobot
import com.test.news.robothelper.NewsScreenRobot
import com.test.news.utilities.Utilities
import org.junit.*
import org.junit.runner.RunWith
class LoginTest {

    @get:Rule
    var activityRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java, true, false)

    private val loginRobot = LoginScreenRobot()
    private val newsRobot = NewsScreenRobot()
    var utilities = Utilities()

    @Before
    fun wifiAndDataEnabled() {
        utilities.enableWifiAndData()
    }

    @Before
    fun setup() {
        utilities.launchActivity()
    }

    // #1
    //Scenario 1 - Launch login and check elements.
    @Test
    fun startAppCheckFields() {
        loginRobot.launchLoginScreenCheckElements(activityRule)
    }

    //Scenario 2 - User login failed after failed username.
    @Test
    fun loginFail() {
        loginRobot.checkLoginFailedUsername(activityRule)
    }

    //Scenario 3 - User login success check navigation to News Activity.
    @Test
    fun loginSuccess() {
        loginRobot.checkLoginSuccess(activityRule)
    }

    //Scenario 4 - User logs in and navigates to News Activity.
    // Then exits app and should be directed to NewsActivity upon returning.
    @Test
    fun loggedInUserExitsOnReturnNavigatedToNewsActivity() {
        loginRobot.checkLoginSuccess(activityRule)
        loginRobot.repeatFor { (activityRule.activity.isFinishing) }
        newsRobot.newsActivityIsDisplayed()
        utilities.appToBackgroundThenResume()
        newsRobot.newsActivityIsDisplayed()
    }

}