package com.ae.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.data.repository.characterlist.CharacterListRepository
import com.ae.util.mockCharacterList
import com.ae.util.mockOffset
import com.ae.domain.model.Resource
import com.ae.util.mockLimit

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
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
        val expectedList = Resource.Success(mockCharacterList)

        coEvery { mockRepository.getAllCharacters(mockOffset, mockLimit) } returns expectedList

        val result = useCase.invoke(mockOffset, mockLimit) as Resource.Success
        MatcherAssert.assertThat(result, Matchers.`is`(expectedList))
        MatcherAssert.assertThat(result.data, Matchers.not(emptyList()))
    }

    @Test
    fun `Get character list should be fail`() = runBlockingTest {
        val expectedResponse = Resource.Error(Exception(""))

        coEvery { mockRepository.getAllCharacters(mockOffset, mockLimit) } returns expectedResponse

        val result = useCase.invoke(mockOffset, mockLimit) as Resource.Error
        MatcherAssert.assertThat(result, Matchers.`is`(expectedResponse))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}