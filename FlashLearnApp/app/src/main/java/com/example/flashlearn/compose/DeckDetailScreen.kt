package com.example.flashlearn.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun DeckDetailScreen(
    navController: NavController,
    viewModel: DeckDetailViewModel = viewModel(),
    deckName: String,
    onNavigateUp: () -> Unit,
    onAddCard: () -> Unit
) {
    // Observe changes to cardAdded in the saved state handle
    val cardAdded = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<Boolean>("cardAdded")
        ?.observeAsState()

    // Trigger card loading when the screen is first loaded or a card is added
    LaunchedEffect(deckName, cardAdded?.value) {
        viewModel.refreshCards(deckName)
        // Reset the cardAdded flag to avoid redundant refreshes
        if (cardAdded?.value == true) {
            navController.currentBackStackEntry?.savedStateHandle?.set("cardAdded", false)
        }
    }

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

        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.cardList) { card ->
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

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddCard) {
            Text(text = "Add Card")
        }
    }
}
