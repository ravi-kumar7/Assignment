package com.android.assignment.ui.main


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.assignment.R
import com.android.assignment.util.DummyData
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainFragmentTest : TestCase() {

    @Before
    fun setup() {
        launchFragmentInContainer<MainFragment>(null, R.style.Theme_Assignment)
    }

    @Test
    fun factsListDisplayed() {
        onView(withId(R.id.rv_items)).check(matches(isDisplayed()))
    }

    @Test
    fun factsDataTest() {
        onData(equalTo(DummyData.getData()[0].title))
        onData(equalTo(DummyData.getData()[1].title))
        onData(equalTo(DummyData.getData()[2].title))
    }
}