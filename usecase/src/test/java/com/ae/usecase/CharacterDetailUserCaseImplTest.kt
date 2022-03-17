package com.ae.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.data.repository.characterdetail.CharacterDetailRepository
import com.ae.domain.model.Resource
import com.ae.util.mockCharacterDetail
import com.ae.util.mockCharacterId
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class CharacterDetailUserCaseImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var mockRepository: CharacterDetailRepository

    private lateinit var mockCharacterDetailUserCase: CharacterDetailUserCase

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockCharacterDetailUserCase = CharacterDetailUserCaseImpl(mockRepository)
    }

    @Test
    fun `Get characters detail from Repository should be success and return character detail`() =
        runBlocking {
            val expectedList = Resource.Success(mockCharacterDetail)

            coEvery {
                mockRepository.getCharacterById(mockCharacterId)
            } returns expectedList

            val result = mockCharacterDetailUserCase.invoke(mockCharacterId) as Resource.Success
            MatcherAssert.assertThat(result, Matchers.`is`(expectedList))
            MatcherAssert.assertThat(result.data, Matchers.not(emptyList()))
        }

    @Test
    fun `Get characters detail from Repository should be fail and return character detail`() =
        runBlocking {
            val expectedResponse = Resource.Error(Exception(""))

            coEvery {
                mockRepository.getCharacterById(mockCharacterId)
            } returns expectedResponse

            val result = mockCharacterDetailUserCase.invoke(mockCharacterId) as Resource.Error
            MatcherAssert.assertThat(result, Matchers.`is`(expectedResponse))
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}