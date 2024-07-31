package com.example.flashlearn.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashlearn.R
import com.example.flashlearn.compose.viewmodel.ManageDecksViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lbl_manage_decks),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {
                items(manageDecksViewModel.deckList) { deck ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .clickable { onDeckSelected(deck.category) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = deck.category,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            IconButton(onClick = { manageDecksViewModel.deleteDeck(deck.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(id = R.string.lbl_bt_delete)
                                )
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 16.dp, start = 16.dp)
        ) {
            Button(
                onClick = onNavigateUp,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.lbl_bt_back))
            }
            Button(onClick = onAddDeck) {
                Text(text = stringResource(id = R.string.lbl_bt_add_deck))
            }
        }
    }
}
