package com.ae.requestmanager.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.data.service.CharacterService
import com.ae.domain.model.CharactersResponse
import com.ae.util.JsonReaderUtil
import com.ae.util.JsonToCharacterResponse
import com.ae.util.mockCharacterId
import com.ae.util.mockLimit
import com.ae.util.mockOffset
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
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class CharacterRemoteDataSourceImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var characterService: CharacterService

    private lateinit var mockRemoteDataSource: CharactersRemoteDataSource
    private lateinit var mockWebServer: MockWebServer
    private lateinit var mockServerResponseSuccess: String
    private lateinit var mockServerResponseFail: String
    private lateinit var mockServerDetailResponseSuccess: String
    private lateinit var mockServerDetailResponseFail: String

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        mockRemoteDataSource = CharacterRemoteDataSourceImpl(characterService)
        setupJsonFiles()
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
        MatcherAssert.assertThat(result, not(""))
        MatcherAssert.assertThat(result, containsString("\"results\""))
        MatcherAssert.assertThat(result, containsString("\"description\""))
    }

    @Test
    fun `Get characters detail from Server should be success`() {
        val expectResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockServerDetailResponseSuccess)

        mockWebServer.enqueue(expectResponse)

        val result = expectResponse.getBody()?.readUtf8()
        MatcherAssert.assertThat(result, not(""))
        MatcherAssert.assertThat(result, containsString("\"id\""))
        MatcherAssert.assertThat(result, containsString("\"description\""))
    }

    @Test
    fun `Get characters list from Server should be success and return character list`() =
        runBlocking {
            val expectResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockServerResponseSuccess)

            mockWebServer.enqueue(expectResponse)

            val expectedCharacterResponse: CharactersResponse =
                expectResponse.getBody()?.readUtf8()?.JsonToCharacterResponse()
                    ?: CharactersResponse()

            coEvery {
                characterService.getAllCharacters(mockOffset, mockLimit)
            } returns expectedCharacterResponse

            val result = mockRemoteDataSource.getAllCharacterListByPageRemote(mockOffset, mockLimit)
            val emptyCharacterResponse = CharactersResponse()
            MatcherAssert.assertThat(result, `is`(expectedCharacterResponse))
            MatcherAssert.assertThat(result, not(emptyCharacterResponse))
        }

    @Test
    fun `Get characters detail from Server should be success and return character detail`() =
        runBlocking {
            val expectResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockServerDetailResponseSuccess)

            mockWebServer.enqueue(expectResponse)

            val expectedCharacterResponse =
                expectResponse.getBody()?.readUtf8()?.JsonToCharacterResponse()
                    ?: CharactersResponse()

            coEvery {
                characterService.getCharacterById(mockCharacterId)
            } returns expectedCharacterResponse

            val result = mockRemoteDataSource.getCharacterById(mockCharacterId)
            val emptyCharacterResponse = CharactersResponse()
            MatcherAssert.assertThat(result, `is`(expectedCharacterResponse))
            MatcherAssert.assertThat(result, not(emptyCharacterResponse))
        }

    @Test
    fun `Get characters list from Server should be fail`() {
        val expectResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_CONFLICT)
            .setBody(mockServerResponseFail)

        mockWebServer.enqueue(expectResponse)

        val result = expectResponse.getBody()?.readUtf8()
        MatcherAssert.assertThat(result, not(""))
        MatcherAssert.assertThat(result, containsString("\"code\""))
        MatcherAssert.assertThat(result, containsString("\"status\""))
    }

    @Test
    fun `Get characters detail from Server should be fail`() {
        val expectResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(mockServerDetailResponseFail)

        mockWebServer.enqueue(expectResponse)

        val result = expectResponse.getBody()?.readUtf8()
        MatcherAssert.assertThat(result, not(""))
        MatcherAssert.assertThat(result, containsString("\"code\""))
        MatcherAssert.assertThat(result, containsString("\"status\""))
        MatcherAssert.assertThat(result, containsString("We couldn't find that character"))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }

    private fun setupJsonFiles() {
        mockServerResponseSuccess =
            JsonReaderUtil.readJsonFromFile("characters_response_success.json")
        mockServerResponseFail = JsonReaderUtil.readJsonFromFile("characters_response_fail.json")
        mockServerDetailResponseSuccess =
            JsonReaderUtil.readJsonFromFile("character_detail_success.json")
        mockServerDetailResponseFail = JsonReaderUtil.readJsonFromFile("character_detail_fail.json")
    }
}