package com.test.news.robothelper

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import com.test.news.basePage.BasePage
import org.hamcrest.Matchers.allOf

class NewsScreenRobot : BasePage(){

    fun newsActivityIsDisplayed() {
        repeatFor {
            onView(withId(R.id.action_bar)).check(matches(isDisplayed())) } }

    fun failedToLoadNewsMessageIsSeen() {
        onView(withId(R.id.textViewError)).check(matches(withText("Failed to load news"))); }

    fun recyclerviewImageVisible() {
        repeatFor {
            onView(allOf(withId(R.id.recyclerViewNews), hasDescendant(withId(R.id.imageView))))
                .check(matches(isCompletelyDisplayed())) } }

    fun clickOnFirstNewsImageWhenVisible() {
        onView(allOf(withId(R.id.recyclerViewNews), hasDescendant(withId(R.id.imageView))))
            .perform(ViewActions.click()) }
}