package com.test.news.robothelper

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.test.news.R
import com.test.news.basePage.BasePage
import com.test.news.features.login.presentation.LoginActivity


class LoginScreenRobot : BasePage() {

    private val UserNameField = withId(R.id.editTextUserName)
    private val PasswordField = withId(R.id.editTextPassword)
    private val loginButton = withId(R.id.buttonLogin)


    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
        private const val INVALID_USERNAME_ERROR_TEXT = "Wrong user name"
    }


    fun launchLoginScreenCheckElements(testRule: ActivityTestRule<LoginActivity>) {

        onView(UserNameField).check(matches(isDisplayed()))
        onView(PasswordField).check(matches(isDisplayed()))
        onView(loginButton).check(matches(isDisplayed()))
    }

    fun checkLoginFailedUsername(testRule: ActivityTestRule<LoginActivity>) {
        enterUserName("test")
        enterPassword("321")
        clickLoginButton()
        wrongUserNameError()
    }

    fun checkLoginSuccess(testRule: ActivityTestRule<LoginActivity>) {
        enterUserName(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()
        newActivityCheck()
    }

    private fun enterUserName(userName: String) {
        onView(UserNameField).perform(clearText(), typeText(userName))
    }

    private fun enterPassword(password: String) {
        onView(PasswordField).perform(clearText(), typeText(password))
    }

    private fun clickLoginButton() {
        onView(loginButton).perform(click())
    }

    fun wrongUserNameError() {
        onView(hasErrorText(INVALID_USERNAME_ERROR_TEXT)).check(matches(isDisplayed()))
    }

    fun newActivityCheck() {
        onView(withId(R.id.action_bar)).check(matches(isDisplayed()))
    }

}
