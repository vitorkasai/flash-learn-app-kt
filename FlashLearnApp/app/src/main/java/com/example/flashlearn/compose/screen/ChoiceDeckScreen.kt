package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashlearn.R
import com.example.flashlearn.compose.viewmodel.ChoiceDeckViewModel
import com.example.flashlearn.repository.model.Card

@Composable
fun ChoiceDeckScreen(
    choiceDeckViewModel: ChoiceDeckViewModel = viewModel(),
    onNavigateUp: () -> Unit = {},
    onDeckSelected: (cards: List<Card>) -> Unit
) {
    LaunchedEffect(Unit) {
        choiceDeckViewModel.refreshDecks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp) // Menor padding
    ) {
        // Remover o botão de voltar do início

        Text(
            text = stringResource(id = R.string.lbl_card_decks),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp) // Menor padding vertical
        )

        LazyColumn(
            modifier = Modifier.weight(1f) // Isso faz a LazyColumn ocupar o espaço restante
        ) {
            itemsIndexed(choiceDeckViewModel.deckList) { _, deck ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp) // Menor padding vertical
                        .clickable { onDeckSelected(deck.cards) } // Torna o Card clicável
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp) // Menor padding
                    ) {
                        Text(
                            text = deck.category,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }

        Button(
            onClick = { onNavigateUp() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // Alinhar ao centro horizontalmente
                .padding(vertical = 16.dp) // Padding para separar do conteúdo acima
        ) {
            Text(text = stringResource(id = R.string.lbl_bt_back_homescreen))
        }
    }
}

