package com.android.assignment.ui

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.assignment.R
import junit.framework.TestCase
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase()
{

    @Test
    fun testTitle() {
        ActivityScenario.launch(MainActivity::class.java)
        onData(allOf(instanceOf(TextView::class.java), Matchers.equalTo("About Canada")))
        onData(
            allOf(
                instanceOf(TextView::class.java),
                Matchers.equalTo("Transportation")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.swipe_refresh_view))
            .perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withText("Syncing data…"))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                    )
                )
            )
    }
}