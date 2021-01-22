package com.android.assignment.ui

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.TestCase
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : TestCase()
{

    @Test
    fun testTitle(){
        ActivityScenario.launch(MainActivity::class.java)
        onData(allOf(instanceOf(TextView::class.java), Matchers.equalTo("About Canada")))
        onData(
            allOf(
                instanceOf(TextView::class.java),
                Matchers.equalTo("Transportation")
            )
        )
    }
}