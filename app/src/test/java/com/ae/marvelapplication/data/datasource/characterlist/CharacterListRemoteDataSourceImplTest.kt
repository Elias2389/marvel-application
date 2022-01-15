package com.ae.marvelapplication.data.datasource.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelapplication.data.service.CharacterService
import com.ae.marvelapplication.dto.dto.CharactersResponse
import com.ae.marvelapplication.util.JsonReaderUtil
import com.ae.marvelapplication.util.JsonToCharacterResponse
import com.ae.marvelapplication.util.mockLimit
import com.ae.marvelapplication.util.mockOffset
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import java.net.HttpURLConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharacterListRemoteDataSourceImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var characterService: CharacterService

    private lateinit var mockRemoteDataSource: CharacterListRemoteDataSource
    private lateinit var mockWebServer: MockWebServer
    private lateinit var mockServerResponseSuccess: String
    private lateinit var mockServerResponseFail: String

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockRemoteDataSource = CharacterListRemoteDataSourceImpl(characterService)
        mockServerResponseSuccess =
            JsonReaderUtil.readJsonFromFile("characters_response_success.json")
        mockServerResponseFail = JsonReaderUtil.readJsonFromFile("characters_response_fail.json")
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `Get characters list from Server should be success`() {
        val expectResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockServerResponseSuccess)

        mockWebServer.enqueue(expectResponse)

        val result = expectResponse.getBody()?.readUtf8()
        assertThat(result, not(""))
        assertThat(result, Matchers.containsString("\"results\""))
        assertThat(result, Matchers.containsString("\"description\""))
    }

    @Test
    fun `Get characters list from Server should be success and return character list`() =
        runBlocking {
            val expectResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockServerResponseSuccess)

            mockWebServer.enqueue(expectResponse)

            val expectedCharacterResponse =
                expectResponse.getBody()?.readUtf8()?.JsonToCharacterResponse()
                    ?: CharactersResponse()

            coEvery {
                characterService.getAllCharacters(mockOffset, mockLimit)
            } returns expectedCharacterResponse

            val result = mockRemoteDataSource.getAllCharacterListByPageRemote(mockOffset, mockLimit)
            val emptyCharacterResponse = CharactersResponse()
            assertThat(result, `is`(expectedCharacterResponse))
            assertThat(result, not(emptyCharacterResponse))
        }

    @Test
    fun `Get characters list from Server should be fail`() {
        val expectResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_CONFLICT)
            .setBody(mockServerResponseFail)

        mockWebServer.enqueue(expectResponse)

        val result = expectResponse.getBody()?.readUtf8()
        assertThat(result, not(""))
        assertThat(result, Matchers.containsString("\"code\""))
        assertThat(result, Matchers.containsString("\"status\""))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }
}