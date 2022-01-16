package com.ae.marvelapplication.data.datasource.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelapplication.data.dao.ResultItemDao
import com.ae.marvelapplication.mapper.toResultsItemEntity
import com.ae.marvelapplication.util.mockCharacterList
import com.ae.marvelapplication.util.mockLimit
import com.ae.marvelapplication.util.mockOffset
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
import org.junit.After
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharacterLocalDataSourceImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var characterDao: ResultItemDao

    private lateinit var mockCharacterLocalDataSource: CharacterLocalDataSource

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockCharacterLocalDataSource = CharacterLocalDataSourceImpl(characterDao)
    }

    @Test
    fun `Get characters list from DB should be success`() = runBlockingTest {
        val expectList = mockCharacterList.toResultsItemEntity()

        coEvery {
            characterDao.getAllCharacters(
                mockOffset,
                mockLimit
            )
        } returns expectList

        val result = mockCharacterLocalDataSource.getAllCharacterListLocal(mockOffset, mockLimit)
        assertThat(expectList, `is`(result))
        assertThat(expectList, not(emptyList()))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}