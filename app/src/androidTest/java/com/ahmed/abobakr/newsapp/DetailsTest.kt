package com.ahmed.abobakr.newsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsTest: BaseUITest() {

    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayImageNews(){
        Thread.sleep(1000)
        navigateToDetailsScreen()

        assertDisplayed(R.id.imgNewsDetails)
    }

    @Test
    fun displayDetailsText(){
        Thread.sleep(1000)
        navigateToDetailsScreen()

        assertDisplayed(R.id.tvNewsDetails)
    }

    @Test
    fun displayAuthorText(){
        Thread.sleep(1000)
        navigateToDetailsScreen()

        onView(withId(R.id.tvAuthor))
            .check(matches(isDisplayed()))
            .check(matches(withText("Ryan Young")))
    }

    @Test
    fun displayArticleText(){
        Thread.sleep(1000)
        navigateToDetailsScreen()

        onView(withId(R.id.tvDate))
            .check(matches(isDisplayed()))
            .check(matches(withText("2023-07-21")))
    }

    private fun navigateToDetailsScreen(){
        onView(
            Matchers.allOf(
                withId(R.id.tvSource),
                isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.rvNewsList), 0))
            )
        ).perform(click())
    }

}