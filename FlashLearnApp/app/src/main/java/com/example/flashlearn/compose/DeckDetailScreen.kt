package com.example.flashlearn.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashlearn.repository.model.Card

@Composable
fun DeckDetailScreen(
    deckName: String,
    cards: List<Card>,
    onNavigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Button(onClick = onNavigateUp) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = deckName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(cards) { card ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Front: ${card.front}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(text = "Back: ${card.back}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
