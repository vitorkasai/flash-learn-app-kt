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
        // Prepare data
        val mockCards = listOf(
            Card(id = 1, front = "Front1", back = "Back1"),
            Card(id = 2, front = "Front2", back = "Back2")
        )
        val mockDecks = listOf(
            Deck(id = 1, category = "Science", cards = mockCards),
            Deck(id = 2, category = "Math", cards = mockCards)
        )

        // Simular resposta bem-sucedida
        Mockito.`when`(mockBackendInterface.getAllDecks())
            .thenReturn(Response.success(mockDecks))

        // Chamar o método a ser testado
        val result = deckRepository.getAllDecks().toList()

        // Verificar resultados
        assertEquals(mockDecks, result.first())
    }

    @Test
    fun `getAllDecks should throw an exception on failure`() = runTest {
        // Preparar dados
        val errorMessage = "Error"

        // Simular resposta com falha
        Mockito.`when`(mockBackendInterface.getAllDecks())
            .thenReturn(Response.error(400, ResponseBody.create(null, errorMessage)))

        try {
            // Chamar o método a ser testado
            deckRepository.getAllDecks().toList()
            fail("Exception expected but not thrown")
        } catch (e: Exception) {
            // Verificar se a exceção esperada foi lançada
            val expectedMessage = "Falha ao retornar decks: $errorMessage"
            assertEquals(expectedMessage, e.message)
        }
    }
}
