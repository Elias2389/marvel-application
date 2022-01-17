package com.ae.marvelapplication.ui.characterlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.ui.characterlist.usecase.CharacterListUseCase
import com.ae.marvelapplication.util.mockCharacterList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var mockUseCase: CharacterListUseCase

    @MockK(relaxed = true)
    private lateinit var mockEventObserver: Observer<Resource<List<ResultsItem>>>

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        setupViewModel()
    }

    @Test
    fun `Get character list should be success`() = runBlockingTest {
        val expectedList = Resource.Success(mockCharacterList)

        coEvery { mockUseCase.invoke(any(), any()) } returns expectedList

        viewModel.getAllCharactersByPaging()

        coVerify { mockEventObserver.onChanged(eq(expectedList)) }
    }

    @Test
    fun `Get character list should be success and return list`() = runBlockingTest {
        val expectedList = Resource.Success(mockCharacterList)

        coEvery { mockUseCase.invoke(any(), any()) } returns expectedList

        viewModel.getAllCharactersByPaging()

        val response = viewModel.getEvents.value
        assertThat(expectedList, `is`(response))
    }

    @Test
    fun `Get character list should be fail`() = runBlockingTest {
        val expectedException = Exception("")
        val expectedResponse = Resource.Error(expectedException)

        coEvery { mockUseCase.invoke(any(), any()) } throws expectedException

        viewModel.getAllCharactersByPaging()

        coVerify { mockEventObserver.onChanged(eq(expectedResponse)) }
    }

    @Test
    fun `Get character list should be fail and return error`() = runBlockingTest {
        val expectedException = Exception("")
        val expectedResponse = Resource.Error(expectedException)

        coEvery { mockUseCase.invoke(any(), any()) } throws expectedException

        viewModel.getAllCharactersByPaging()

        val errorResponse = viewModel.getEvents.value
        assertThat(expectedResponse, `is`(errorResponse))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    private fun setupViewModel() {
        viewModel = CharacterListViewModel(mockUseCase)
            .apply { getEvents.observeForever(mockEventObserver) }
    }
}