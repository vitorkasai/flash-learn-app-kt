package com.example.flashlearn

import com.example.flashlearn.repository.DeckRepository
import com.example.flashlearn.repository.model.Deck
import com.example.flashlearn.repository.model.Card
import com.example.flashlearn.repository.retrofit.BackendInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import retrofit2.Response
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DeckRepositoryTest {

    private lateinit var deckRepository: DeckRepository
    private val mockBackendInterface = Mockito.mock(BackendInterface::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        deckRepository = DeckRepository().apply {
            client = mockBackendInterface
        }
    }

    @Test
    fun `getAllDecks should return decks successfully`() = runTest {
        val mockCards = listOf(
            Card(id = 1, front = "Front1", back = "Back1"),
            Card(id = 2, front = "Front2", back = "Back2")
        )
        val mockDecks = listOf(
            Deck(id = 1, category = "Science", cards = mockCards),
            Deck(id = 2, category = "Math", cards = mockCards)
        )

        Mockito.`when`(mockBackendInterface.getAllDecks())
            .thenReturn(Response.success(mockDecks))

        val result = deckRepository.getAllDecks().toList()

        assertEquals(mockDecks, result.first())
    }

    @Test
    fun `getAllDecks should throw an exception on failure`() = runTest {
        val errorMessage = "Error"

        Mockito.`when`(mockBackendInterface.getAllDecks())
            .thenReturn(Response.error(400, ResponseBody.create(null, errorMessage)))

        try {
            deckRepository.getAllDecks().toList()
            fail("Exception expected but not thrown")
        } catch (e: Exception) {
            val expectedMessage = "Falha ao retornar decks: $errorMessage"
            assertEquals(expectedMessage, e.message)
        }
    }
}
