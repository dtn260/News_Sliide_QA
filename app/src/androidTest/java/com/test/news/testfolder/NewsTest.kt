package com.test.news.testfolder

import androidx.test.rule.ActivityTestRule
import com.test.news.basePage.BasePage
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.robothelper.LoginScreenRobot
import com.test.news.robothelper.NewsScreenRobot
import com.test.news.utilities.Utilities
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NewsTest : BasePage() {

    @get:Rule
    var activityRule = ActivityTestRule<LoginActivity>(
        LoginActivity::class.java)

    private val loginRobot = LoginScreenRobot()
    private val newsRobot = NewsScreenRobot()
    var utilities = Utilities()

    @Before
    fun userLoginSucceeds() {
        utilities.enableWifiAndData()
    }

    @After
    fun enableWifi() {
        utilities.enableWifiAndData()
    }


    // #2
    //Scenario 1 - Login successful with internet connection and images in recyclerview are displayed.
    @Test
    fun imagesLoadedForUser() {
        loginRobot.checkLoginSuccess(activityRule)
        loginRobot.repeatFor { activityRule.activity.isFinishing }
        newsRobot.newsActivityIsDisplayed()
        newsRobot.recyclerviewImageVisible()
    }

    //Scenario 2 - Login successful but no internet connection and fail message shown.
    @Test
    fun imagesFailedToLoadDueToNoInternet() {
        utilities.disableWifiAndData()
        loginRobot.checkLoginSuccess(activityRule)
        loginRobot.repeatFor { activityRule.activity.isFinishing }
        newsRobot.newsActivityIsDisplayed()
        newsRobot.failedToLoadNewsMessageIsSeen()
    }

    //Scenario 3 - Login successful and images are loaded successfully then user navigated
    // to external browser when clicking on image.
    @Test
    fun newsImageIsClickedAndUserIsNavigatedToExternalBrowser() {
        loginRobot.checkLoginSuccess(activityRule)
        loginRobot.repeatFor { activityRule.activity.isFinishing }
        newsRobot.newsActivityIsDisplayed()
        newsRobot.recyclerviewImageVisible()
        newsRobot.clickOnFirstNewsImageWhenVisible()
        utilities.userIsNavigatedToChrome()
    }

}