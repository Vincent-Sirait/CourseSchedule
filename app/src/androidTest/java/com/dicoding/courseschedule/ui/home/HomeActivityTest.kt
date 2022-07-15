package com.dicoding.courseschedule.ui.home

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @get:Rule
    var rule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test

    fun onClickFab_shouldNavigateTo_addCourseActivity() {
        onView(withId(R.id.action_add)).perform(click())
        intended(hasComponent(AddCourseActivity::class.java.name))

        onView(withText(R.string.title_activity_list)).check(matches(isDisplayed()))
        onView(withId(R.id.action_insert)).check(matches(isDisplayed()))

        onView(withId(R.id.et_course)).check(matches(isDisplayed()))
        onView(withId(R.id.et_course)).check(matches(withHint(R.string.course_name)))

        onView(withId(R.id.sp_day)).check(matches(isDisplayed()))
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.sunday)))

        onView(withId(R.id.sp_day)).perform(click())
        onView(withText(R.string.sunday)).perform(click())
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.monday)))

        onView(withId(R.id.sp_day)).perform(click())
        onView(withText(R.string.monday)).perform(click())
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.tuesday)))

        onView(withId(R.id.sp_day)).perform(click())
        onView(withText(R.string.tuesday)).perform(click())
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.wednesday)))

        onView(withId(R.id.sp_day)).perform(click())
        onView(withText(R.string.wednesday)).perform(click())
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.thursday)))

        onView(withId(R.id.sp_day)).perform(click())
        onView(withText(R.string.thursday)).perform(click())
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.friday)))

        onView(withId(R.id.sp_day)).perform(click())
        onView(withText(R.string.friday)).perform(click())
        onView(withId(R.id.sp_day)).check(matches(withSpinnerText(R.string.saturday)))

        onView(withId(R.id.tv_start)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_end)).check(matches(isDisplayed()))

        onView(withId(R.id.et_lecture)).check(matches(isDisplayed()))
        onView(withId(R.id.et_lecture)).check(matches(withHint(R.string.lecturer)))

        onView(withId(R.id.et_note)).check(matches(isDisplayed()))
        onView(withId(R.id.et_note)).check(matches(withHint(R.string.note)))
    }


    private fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                currentActivity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0)
            }
        }
        return currentActivity
    }

    fun addCourse() {
        val currentActivity = getCurrentActivity()
        assertTrue(currentActivity?.javaClass == AddCourseActivity::class.java)

        onView(withId(R.id.action_add)).check(matches(isDisplayed())).perform(click())

    }


}