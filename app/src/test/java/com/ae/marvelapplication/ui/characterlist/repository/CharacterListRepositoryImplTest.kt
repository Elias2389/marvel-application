package com.ae.marvelapplication.ui.characterlist.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.data.connectionchecker.CheckConnection
import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.mapper.toResultsItemEntity
import com.ae.marvelapplication.util.mockCharacterList
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

    @MockK(relaxed = true)
    private lateinit var localDataSource: CharacterLocalDataSource

    @MockK(relaxed = true)
    private lateinit var checkConnect: CheckConnection

    private lateinit var mockRepository: CharacterListRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockRepository = CharacterListRepositoryImpl(remoteDataSource, localDataSource, checkConnect)
    }

    @Test
    fun `Get characters list from RemoteDataSource should be success and return character list`() =
        runBlocking {
            val expectedListEntity = mockCharacterList.toResultsItemEntity()
            val expectedList = mockCharacterList

            coEvery {
                localDataSource.getAllCharacterListLocal(mockOffset, mockLimit)
            } returns expectedListEntity

            val result = mockRepository.getAllCharacters(mockOffset, mockLimit) as Resource.Success
            assertThat(result.data, `is`(expectedList))
            assertThat(result.data, not(emptyList()))
        }

    @Test
    fun `Get characters list from RemoteDataSource should be fail`() =
        runBlocking {
            val expectedException = Exception("")

            coEvery {
                localDataSource.getAllCharacterListLocal(mockOffset, mockLimit)
            } throws expectedException

            val result = mockRepository.getAllCharacters(mockOffset, mockLimit) as Resource.Error
            assertThat(result.exception, `is`(expectedException))
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}