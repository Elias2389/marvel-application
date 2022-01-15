package com.ae.marvelapplication.ui.characterlist.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelapplication.ui.characterlist.repository.CharacterListRepository
import com.ae.marvelapplication.util.mockCharacterList
import com.ae.marvelapplication.util.mockLimit
import com.ae.marvelapplication.util.mockOffset
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharacterListUseCaseImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var mockRepository: CharacterListRepository

    private lateinit var useCase: CharacterListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        useCase = CharacterListUseCaseImpl(mockRepository)
    }

    @Test
    fun `Get character list should be success`() = runBlockingTest {
        val expectedList = mockCharacterList

        coEvery { mockRepository.getAllCharacters(mockOffset, mockLimit) } returns expectedList

        val result = useCase.invoke(mockOffset, mockLimit)
        assertThat(result, `is`(expectedList))
        assertThat(result, not(emptyList()))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}