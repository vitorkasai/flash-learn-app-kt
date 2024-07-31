package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import com.example.flashlearn.compose.component.DeckItem
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
                DeckItem(
                    category = deck.category,
                    onDeckClick = { onDeckSelected(deck.category) }
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))

        Button(onClick = onAddDeck) {
            Text(text = stringResource(id = R.string.lbl_bt_add_deck))
        }
    }
}
