package com.ae.marvelapplication.ui.characterdetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ae.marvelapplication.data.response.Resource
import com.ae.domain.model.ResultsItem
import com.ae.marvelapplication.presentation.viewmodel.CharacterDetailViewModel
import com.ae.marvelapplication.usecase.CharacterDetailUserCase
import com.ae.marvelapplication.util.getOrAwaitValue
import com.ae.marvelapplication.util.mockCharacterDetail
import com.ae.marvelapplication.util.mockCharacterId
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
class CharacterDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var mockUseCase: CharacterDetailUserCase

    @MockK(relaxed = true)
    private lateinit var mockObserver: Observer<Resource<List<ResultsItem>>>

    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = CharacterDetailViewModel(mockUseCase)
    }

    @Test
    fun `Get character detail should be success`() = runBlockingTest {
        val charactersExpect = Resource.Success(mockCharacterDetail)

        coEvery { mockUseCase.invoke(mockCharacterId) } returns charactersExpect

        viewModel.getCharacterById(mockCharacterId).observeForever(mockObserver)
        viewModel.getCharacterById(mockCharacterId).getOrAwaitValue()

        coVerify { mockObserver.onChanged(eq(charactersExpect)) }
    }

    @Test
    fun `Get character detail should be success and return value`() = runBlockingTest {
        val charactersExpect = Resource.Success(mockCharacterDetail)

        coEvery { mockUseCase.invoke(mockCharacterId) } returns charactersExpect

        viewModel.getCharacterById(mockCharacterId).observeForever(mockObserver)
        val result = viewModel.getCharacterById(mockCharacterId).getOrAwaitValue() as Resource.Success

        assertThat(charactersExpect, `is`(result))
    }

    @Test
    fun `Get character detail should be fail`() = runBlockingTest {
        val expectedException = Exception("")
        val expectedResponse = Resource.Error(expectedException)

        coEvery { mockUseCase.invoke(mockCharacterId) } throws expectedException

        viewModel.getCharacterById(mockCharacterId).observeForever(mockObserver)
        viewModel.getCharacterById(mockCharacterId).getOrAwaitValue()

        coVerify { mockObserver.onChanged(eq(expectedResponse)) }
    }

    @Test
    fun `Get character detail should be fail and return error`() = runBlockingTest {
        val expectedException = Exception("")
        val expectedResponse = Resource.Error(expectedException)

        coEvery { mockUseCase.invoke(mockCharacterId) } throws expectedException

        viewModel.getCharacterById(mockCharacterId).observeForever(mockObserver)
        val result = viewModel.getCharacterById(mockCharacterId).getOrAwaitValue()

        assertThat(expectedResponse, `is`(result))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}