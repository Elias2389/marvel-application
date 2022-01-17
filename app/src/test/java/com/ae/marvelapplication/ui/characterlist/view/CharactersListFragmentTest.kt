package com.ae.marvelapplication.ui.characterlist.view

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ae.marvelapplication.R
import com.ae.marvelapplication.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@Config(
    application = HiltTestApplication::class,
    instrumentedPackages = ["androidx.loader.content"]
)
class CharactersListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `Show character list container could be success`() {
        launchFragment()
        onView(withId(R.id.constraint_layout_character_list_container))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Show character list could be success`() {
        launchFragment()
        onView(withId(R.id.rv_list_items))
            .check(matches(isDisplayed()))
    }

    private fun launchFragment() {
        val idTheme = R.style.character_app_Theme_MarvelApplication
        val navController = TestNavHostController(getApplicationContext())
        launchFragmentInHiltContainer<CharactersListFragment>(themeResId = idTheme) {
            navController.setGraph(R.navigation.character_app_navigation)
            navController.setCurrentDestination(R.id.character_list_fragment)
        }
    }
}