package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashlearn.R
import com.example.flashlearn.compose.viewmodel.ManageDecksViewModel

@Composable
fun ManageDecksScreen(
    navController: NavController,
    manageDecksViewModel: ManageDecksViewModel = viewModel(),
    onNavigateUp: () -> Unit = {},
    onAddDeck: () -> Unit = {},
    onDeckSelected: (deckName: String) -> Unit
) {
    val deckAdded = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<Boolean>("deckAdded")
        ?.observeAsState()

    LaunchedEffect(Unit, deckAdded?.value) {
        manageDecksViewModel.refreshDecks()
        if (deckAdded?.value == true) {
            navController.currentBackStackEntry?.savedStateHandle?.set("deckAdded", false)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Button(onClick = onNavigateUp) {
                Text(text = stringResource(id = R.string.lbl_bt_back))
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
        Text(
            text = stringResource(id = R.string.lbl_manage_decks),
            style = MaterialTheme.typography.headlineMedium
        )
        LazyColumn {
            items(manageDecksViewModel.deckList) { deck ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = deck.category,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Button(onClick = { onDeckSelected(deck.category) }) {
                        Text(text = "Abrir")
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(onClick = { manageDecksViewModel.deleteDeck(deck.id) }) {
                        Text(text = "Deletar")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))

        Button(onClick = onAddDeck) {
            Text(text = stringResource(id = R.string.lbl_bt_add_deck))
        }
    }
}
