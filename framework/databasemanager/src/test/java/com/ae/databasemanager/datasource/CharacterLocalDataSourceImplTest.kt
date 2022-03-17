package com.ae.databasemanager.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.databasemanager.dao.AppDatabase
import com.ae.databasemanager.dao.ResultItemDao
import com.ae.databasemanager.mapper.toResultsItem
import com.ae.databasemanager.util.mockLimit
import com.ae.databasemanager.util.mockName
import com.ae.databasemanager.util.mockOffset
import com.ae.databasemanager.util.mockResultsItemEntityList
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
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ae.databasemanager.mapper.toResultsItemEntity

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
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
        val expectList = mockResultsItemEntityList.toResultsItem()

        coEvery {
            characterDao.getAllCharacters(
                mockOffset,
                mockLimit
            )
        } returns expectList.toResultsItemEntity()

        val result = mockCharacterLocalDataSource.getAllCharacterListLocal(mockOffset, mockLimit)
        MatcherAssert.assertThat(expectList, CoreMatchers.`is`(result))
        MatcherAssert.assertThat(expectList, CoreMatchers.not(emptyList()))
    }

    @Test
    fun `Save character in memory DB should be success`() = runBlocking {
        val expectList = mockResultsItemEntityList.toResultsItem()
        characterLocalDataSource.saveCharacterLocal(expectList[1])

        val result = characterLocalDataSource.getCharacterListLocalById(1)

        MatcherAssert.assertThat(result, CoreMatchers.`is`(CoreMatchers.notNullValue()))
        MatcherAssert.assertThat(result.name, CoreMatchers.`is`(mockName))
    }

    @Test
    fun `Save characters list in memory DB should be success`() = runBlocking {
        val expectList = mockResultsItemEntityList.toResultsItem()

        expectList.map { item ->
            characterLocalDataSource.saveCharacterLocal(item)
        }

        val result = characterLocalDataSource
            .getAllCharacterListLocal(mockOffset, mockLimit)
        MatcherAssert.assertThat(result.isEmpty(), CoreMatchers.`is`(false))
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