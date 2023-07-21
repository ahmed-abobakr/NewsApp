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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {

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
        Thread.sleep(5000)
        assertDisplayed(R.id.rvNewsList)
        assertRecyclerViewItemCount(R.id.rvNewsList, 20)
        onView(
            allOf(
                withId(R.id.tvNews),
                isDescendantOfA(nthChildOf(withId(R.id.rvNewsList), 0))
            )
        )
            .check(matches(isDisplayed()))
            .check(matches(withText("المحسوسة بالقاهرة 40 وأسوان 46 درجة.. طقس الغد شديد الحرارة بمعظم الأنحاء - اليوم السابع")))

        onView(
            allOf(
                withId(R.id.tvSource),
                isDescendantOfA(nthChildOf(withId(R.id.rvNewsList), 0))
            )
        )
            .check(matches(isDisplayed()))
            .check(matches(withText("Google News")))

    }

    private fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }


}