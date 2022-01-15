package com.ae.marvelapplication.ui.characterlist.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.util.characterListMock
import com.ae.marvelapplication.util.mockCharacterResponse
import com.ae.marvelapplication.util.mockCharacterResponseEmptyList
import com.ae.marvelapplication.util.mockLimit
import com.ae.marvelapplication.util.mockOffset
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
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
class CharacterListRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: CharactersRemoteDataSource

    private lateinit var mockRepository: CharacterListRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockRepository = CharacterListRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `Get characters list from RemoteDataSource should be success and return character list`() =
        runBlocking {
            val expectedList = characterListMock
            val expectedCharacterResponse = mockCharacterResponse

            coEvery {
                remoteDataSource.getAllCharacterListByPageRemote(mockOffset, mockLimit)
            } returns expectedCharacterResponse

            val result = mockRepository.getAllCharacters(mockOffset, mockLimit)
            assertThat(result, `is`(expectedList))
            assertThat(result, not(emptyList()))
        }

    @Test
    fun `Get characters list from RemoteDataSource should be error and return list empty`() =
        runBlocking {
            val expectedList = emptyList<ResultsItem>()
            val expectedCharacterResponse = mockCharacterResponseEmptyList

            coEvery {
                remoteDataSource.getAllCharacterListByPageRemote(mockOffset, mockLimit)
            } returns expectedCharacterResponse

            val result = mockRepository.getAllCharacters(mockOffset, mockLimit)

            assertThat(result, `is`(expectedList))
        }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}