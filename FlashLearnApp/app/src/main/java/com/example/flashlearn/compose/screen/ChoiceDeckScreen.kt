package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.flashlearn.compose.component.DeckItem
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Button(onClick = {
                onNavigateUp()
            }) {
                Text(text = stringResource(id = R.string.lbl_bt_back_homescreen))
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
        Text(
            text = stringResource(id = R.string.lbl_card_decks),
            style = MaterialTheme.typography.headlineSmall
        )
        LazyColumn {
            itemsIndexed(choiceDeckViewModel.deckList) { _, deck ->
                DeckItem(
                    category = deck.category,
                    onDeckClick = { onDeckSelected(deck.cards) }
                )
            }
        }
    }
}
