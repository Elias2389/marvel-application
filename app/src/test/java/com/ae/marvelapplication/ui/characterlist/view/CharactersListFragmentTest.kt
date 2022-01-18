package com.ae.marvelapplication.ui.characterlist.view

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ae.marvelapplication.R
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.util.launchFragmentInHiltContainer
import com.ae.marvelapplication.util.mockCharacter
import com.ae.marvelapplication.util.mockCharacterList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@Config(instrumentedPackages = ["androidx.loader.content"])
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
    fun `Show Empty state could be success`() {
        launchFragment()
        onView(withId(R.id.empty_state_loading))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Show character list recyclerview could be success`() {
        launchFragment()
        onView(withId(R.id.rv_list_items))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Show character list could be success and hide empty state`() {
        val fakeSuccessResponse = Resource.Success(mockCharacterList)
        val idTheme = R.style.character_app_Theme_MarvelApplication
        val navController = TestNavHostController(getApplicationContext())

        launchFragmentInHiltContainer<CharactersListFragment>(themeResId = idTheme) {
            navController.setGraph(R.navigation.character_app_navigation)
            navController.setCurrentDestination(R.id.character_list_fragment)
            val fragment = this as CharactersListFragment
            fragment.handlerResponse(fakeSuccessResponse)
        }

        onView(withId(R.id.rv_list_items))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.empty_state_loading))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun `Show character in recycler view could be success`() {
        val fakeList = listOf(
            mockCharacter.copy(id = 1, name = "Test"),
            mockCharacter.copy(id = 2, name = "Test 1")
        )
        val fakeSuccessResponse = Resource.Success(fakeList)
        val idTheme = R.style.character_app_Theme_MarvelApplication
        val navController = TestNavHostController(getApplicationContext())

        launchFragmentInHiltContainer<CharactersListFragment>(themeResId = idTheme) {
            navController.setGraph(R.navigation.character_app_navigation)
            navController.setCurrentDestination(R.id.character_list_fragment)
            val fragment = this as CharactersListFragment
            fragment.handlerResponse(fakeSuccessResponse)
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
        val idTheme = R.style.character_app_Theme_MarvelApplication
        val navController = TestNavHostController(getApplicationContext())

        launchFragmentInHiltContainer<CharactersListFragment>(themeResId = idTheme) {
            navController.setGraph(R.navigation.character_app_navigation)
            navController.setCurrentDestination(R.id.character_list_fragment)
            val fragment = this as CharactersListFragment
            fragment.handlerResponse(fakeErrorResponse)
        }

        onView(withId(R.id.rv_list_items))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.empty_state_character_list_view))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun `Navigate to Character Detail fragment could be success`() {
        val fakeResultItem = mockCharacter
        val idTheme = R.style.character_app_Theme_MarvelApplication
        val navController = TestNavHostController(getApplicationContext())

        launchFragmentInHiltContainer<CharactersListFragment>(themeResId = idTheme) {
            navController.setGraph(R.navigation.character_app_navigation)
            Navigation.setViewNavController(requireView(), navController)
            val fragment = this as CharactersListFragment
            fragment.goToDetail(fakeResultItem)
        }

        assertThat(navController.currentDestination?.id, `is`(R.id.character_detail_fragment))
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