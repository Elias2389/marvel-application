package com.ae.marvelapplication.data.datasource.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelappication.data.service.CharacterService
import com.ae.marvelappication.dto.Data
import com.ae.marvelapplication.dto.dto.MarvelResponse
import com.ae.marvelapplication.utils.characterMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharacterListRemoteDataSourceImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var mockCService: CharacterService

    lateinit var mockRemoteDataSource: CharacterListRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockRemoteDataSource = CharacterListRemoteDataSourceImpl(mockCService)
    }

    @Test
    fun `Get All characters from API should be success`() = runBlockingTest {
        val expectedList = arrayListOf(characterMock.copy(id = 1), characterMock.copy(id = 2))
        val mockOffset = 0
        val mockLimit = 10
        val expectResponse = MarvelResponse()
            .copy(data = Data(
                offset = mockOffset,
                limit = mockLimit,
                results = expectedList
            ))

        coEvery { mockCService.getAllCharacters(mockOffset,mockLimit) } returns expectResponse

        Assert.assertEquals(expectedList,
            mockRemoteDataSource.getAllCharacterListRemote(mockOffset,mockLimit).data.results)
    }
}