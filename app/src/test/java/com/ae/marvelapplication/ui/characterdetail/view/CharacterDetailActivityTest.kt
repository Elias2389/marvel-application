package com.ae.marvelapplication.ui.characterdetail.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ae.marvelapplication.R
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.util.mockCharacterComics
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@Config(instrumentedPackages = ["androidx.loader.content"])
class CharacterDetailActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    var activityRule = ActivityScenarioRule(CharacterDetailActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `Show character detail could be success and hide empty state`() {
        val fakeSuccessResponse = Resource.Success(mockCharacterComics)

        activityRule.scenario.onActivity {
            it.handlerResponse(fakeSuccessResponse)
        }

        onView(withId(R.id.constraint_layout_character_detail_container))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun `Show character in recycler view could be success`() {
        val fakeSuccessResponse = Resource.Success(mockCharacterComics)

        activityRule.scenario.onActivity {
            it.handlerResponse(fakeSuccessResponse)
        }

        onView(withId(R.id.rv_character_detail_items))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withText("first"))
    }

    @Test
    fun `Show character detail could be fail and show empty state`() {
        val fakeErrorResponse = Resource.Error(Exception("Error"))

        activityRule.scenario.onActivity {
            it.handlerResponse(fakeErrorResponse)
        }

        onView(withId(R.id.rv_character_detail_items))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.empty_state_character_detail_view))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}