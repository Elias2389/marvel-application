package com.ae.marvelapplication.ui.characterdetail.usercase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.ui.characterdetail.repository.CharacterDetailRepository
import com.ae.marvelapplication.ui.characterdetail.repository.CharacterDetailRepositoryImpl
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
            val expectedList = mockCharacterDetail

            coEvery {
                mockRepository.getCharacterById(mockCharacterId)
            } returns expectedList

            val result = mockCharacterDetailUserCase.invoke(mockCharacterId)
            assertThat(result, `is`(expectedList))
            assertThat(result, not(emptyList()))
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}