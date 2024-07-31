import com.example.flashlearn.repository.CardRepository
import com.example.flashlearn.repository.model.Card
import com.example.flashlearn.repository.retrofit.BackendInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class CardRepositoryTest {

    private lateinit var cardRepository: CardRepository
    private val mockBackendInterface = Mockito.mock(BackendInterface::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cardRepository = CardRepository().apply {
            client = mockBackendInterface
        }
    }

    @Test
    fun getCardsByCategoryTest() = runTest {
        // Prepare data
        val category = "Science"
        val mockCards = listOf(
            Card(id = 1, front = "Front 1", back = "Back 1"),
            Card(id = 2, front = "Front 2", back = "Back 2")
        )

        Mockito.`when`(mockBackendInterface.findCardsByCategory(category))
            .thenReturn(Response.success(mockCards))

        val result = cardRepository.getCardsByCategory(category).toList()

        assertEquals(mockCards, result.first())
    }

    @Test
    fun `getCardsByCategory should throw an exception on failure`() = runTest {
        // Preparar dados
        val category = "Science"
        val errorMessage = "Error"

        // Simular resposta com falha
        Mockito.`when`(mockBackendInterface.findCardsByCategory(category))
            .thenReturn(Response.error(400, ResponseBody.create(null, errorMessage)))

        try {
            // Chamar o método a ser testado
            cardRepository.getCardsByCategory(category).toList()
            fail("Exception expected but not thrown")
        } catch (e: Exception) {
            // Verificar se a exceção esperada foi lançada
            val expectedMessage = "Falha ao retornar cards: $errorMessage"
            assertEquals(expectedMessage, e.message)
        }
    }
}
