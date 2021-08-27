package com.test.news.basePage


import junit.framework.AssertionFailedError
import java.lang.Exception

open class BasePage {


    fun repeatFor(codeblock: () -> Unit) {
        repeat(50) {
            try {
                codeblock()
                return
            } catch (e: Exception) {
                Thread.sleep(350)
            } catch (e: AssertionError) {
                Thread.sleep(350)
            } catch (e: AssertionFailedError) {
                Thread.sleep(350)
            }
        }
        throw NoSuchElementException("Unable to find the element, try again!")
    }
}