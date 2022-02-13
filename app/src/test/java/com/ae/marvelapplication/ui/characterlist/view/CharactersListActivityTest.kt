package com.ae.marvelapplication.ui.characterlist.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ae.marvelapplication.R
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.presentation.view.CharactersListActivity
import com.ae.marvelapplication.presentation.view.CharacterDetailActivity
import com.ae.marvelapplication.util.mockCharacter
import com.ae.marvelapplication.util.mockCharacterList
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
class CharactersListActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    var activityRule = ActivityScenarioRule(CharactersListActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `Show character list could be success and hide empty state`() {
        val fakeSuccessResponse = Resource.Success(mockCharacterList)

        activityRule.scenario.onActivity {
            it.handlerResponse(fakeSuccessResponse)
        }

        onView(withId(R.id.rv_list_items))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun `Show character in recycler view could be success`() {
        val fakeList = listOf(
            mockCharacter.copy(id = 1, name = "Test"),
            mockCharacter.copy(id = 2, name = "Test 1")
        )
        val fakeSuccessResponse = Resource.Success(fakeList)

        activityRule.scenario.onActivity {
            it.handlerResponse(fakeSuccessResponse)
        }

        onView(withId(R.id.rv_list_items))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withText("Test"))
        onView(withId(R.id.rv_list_items))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withText("Test 1"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Show character list could be fail and show empty state`() {
        val fakeErrorResponse = Resource.Error(Exception("Error"))

        activityRule.scenario.onActivity {
            it.handlerResponse(fakeErrorResponse)
        }

        onView(withId(R.id.rv_list_items))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.empty_state_character_list_view))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun `Navigate to Character Detail activity could be success`() {
        Intents.init()
        val fakeResultItem = mockCharacter

        activityRule.scenario.onActivity {
            it.goToDetail(fakeResultItem)
        }

        intended(hasComponent(CharacterDetailActivity::class.java.name))
        Intents.release()
    }
}