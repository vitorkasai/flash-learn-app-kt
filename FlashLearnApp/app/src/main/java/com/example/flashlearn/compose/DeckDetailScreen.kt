import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.flashlearn.R
import com.example.flashlearn.repository.model.Card

@Composable
fun DeckDetailScreen(
    cards: List<Card>,
    onNavigateUp: () -> Unit = {}
) {
    var currentCardIndex by remember { mutableIntStateOf(0) }
    var isBackVisible by remember { mutableStateOf(false) }
    val currentCard = cards[currentCardIndex]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Button(onClick = onNavigateUp) {
                Text(text = stringResource(id = R.string.lbl_bt_back))
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
        Text(
            text = "Frente: ${currentCard.front}",
            style = MaterialTheme.typography.headlineMedium
        )
        if (isBackVisible) {
            Text(
                text = "Verso: ${currentCard.back}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(
            onClick = { isBackVisible = !isBackVisible }
        ) {
            Text(text = "Virar")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = {
                if (currentCardIndex < cards.size - 1) {
                    currentCardIndex++
                    isBackVisible = false
                }
            }
        ) {
            Text(text = "Próximo Cartão")
        }
    }
}
