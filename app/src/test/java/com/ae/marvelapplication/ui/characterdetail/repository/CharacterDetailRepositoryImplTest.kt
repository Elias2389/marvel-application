package com.ae.marvelapplication.ui.characterdetail.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.data.repository.characterdetail.CharacterDetailRepository
import com.ae.data.repository.characterdetail.CharacterDetailRepositoryImpl
import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.domain.model.Resource
import com.ae.marvelapplication.util.mockCharacterDetail
import com.ae.marvelapplication.util.mockCharacterDetailResponse
import com.ae.marvelapplication.util.mockCharacterId
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
class CharacterDetailRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: CharactersRemoteDataSource

    private lateinit var mockRepository: CharacterDetailRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockRepository = CharacterDetailRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `Get characters detail from RemoteDataSource should be success and return character detail`() =
        runBlocking {
            val expectedList = mockCharacterDetail
            val expectedCharacterResponse = mockCharacterDetailResponse

            coEvery {
                remoteDataSource.getCharacterById(mockCharacterId)
            } returns expectedCharacterResponse

            val result = mockRepository.getCharacterById(mockCharacterId) as Resource.Success
            assertThat(result.data, `is`(expectedList))
            assertThat(result.data, not(emptyList()))
        }

    @Test
    fun `Get characters detail from RemoteDataSource should be fail and return error`() =
        runBlocking {
            val expectedException = Exception("")

            coEvery {
                remoteDataSource.getCharacterById(mockCharacterId)
            } throws expectedException

            val result = mockRepository.getCharacterById(mockCharacterId) as Resource.Error
            assertThat(result.exception, `is`(expectedException))
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}