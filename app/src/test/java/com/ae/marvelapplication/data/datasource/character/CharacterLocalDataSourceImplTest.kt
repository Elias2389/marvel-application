package com.ae.marvelapplication.data.datasource.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.databasemanager.dao.AppDatabase
import com.ae.databasemanager.dao.ResultItemDao
import com.ae.marvelapplication.mapper.toResultsItemEntity
import com.ae.marvelapplication.util.mockCharacterList
import com.ae.marvelapplication.util.mockLimit
import com.ae.marvelapplication.util.mockName
import com.ae.marvelapplication.util.mockOffset
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class CharacterLocalDataSourceImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var characterDao: ResultItemDao

    private lateinit var mockCharacterLocalDataSource: CharacterLocalDataSource
    private lateinit var characterLocalDataSource: CharacterLocalDataSource
    private lateinit var dataBase: AppDatabase

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        setupInMemoryDataBase()
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

    @Test
    fun `Save character in memory DB should be success`() = runBlocking {
        val expectList = mockCharacterList.toResultsItemEntity()
        characterLocalDataSource.saveCharacterLocal(expectList[1])

        val result = characterLocalDataSource.getCharacterListLocalById(1)

        assertThat(result, `is`(notNullValue()))
        assertThat(result.name, `is`(mockName))
    }

    @Test
    fun `Save characters list in memory DB should be success`() = runBlocking {
        val expectList = mockCharacterList.toResultsItemEntity()

        expectList.map { item ->
            characterLocalDataSource.saveCharacterLocal(item)
        }

        val result = characterLocalDataSource
            .getAllCharacterListLocal(mockOffset, mockLimit)
        assertThat(result.isEmpty(), `is`(false))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        dataBase.close()
    }

    private fun setupInMemoryDataBase() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        characterLocalDataSource = CharacterLocalDataSourceImpl(dataBase.resultItemDao())
    }
}