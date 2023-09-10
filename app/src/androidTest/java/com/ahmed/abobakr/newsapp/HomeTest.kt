package com.ahmed.abobakr.newsapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest: BaseUITest() {

    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayAppTitle(){
        assertDisplayed("NewsApp")
    }

    @Test
    fun displaySearchEditTextTest(){
        assertDisplayed(R.id.editSearch)
    }

    @Test
    fun displayNewsListTest(){
        Thread.sleep(1000)
        assertDisplayed(R.id.rvNewsList)
        assertRecyclerViewItemCount(R.id.rvNewsList, 6)
        onView(
            allOf(
                withId(R.id.tvNews),
                isDescendantOfA(nthChildOf(withId(R.id.rvNewsList), 0))
            )
        )
            .check(matches(isDisplayed()))
            .check(matches(withText("British Open: Follow Tommy Fleetwood, Max Homa, Brooks Koepka and more Friday at Royal Liverpool - Yahoo Sports")))

        onView(
            allOf(
                withId(R.id.tvSource),
                isDescendantOfA(nthChildOf(withId(R.id.rvNewsList), 0))
            )
        )
            .check(matches(isDisplayed()))
            .check(matches(withText("Yahoo Entertainment")))

    }




}