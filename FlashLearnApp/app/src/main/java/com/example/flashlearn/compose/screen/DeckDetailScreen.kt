package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.flashlearn.compose.viewmodel.DeckDetailViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

@Composable
fun DeckDetailScreen(
    navController: NavController,
    viewModel: DeckDetailViewModel = viewModel(),
    deckName: String,
    onNavigateUp: () -> Unit,
    onAddCard: () -> Unit
) {
    val cardAdded = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<Boolean>("cardAdded")
        ?.observeAsState()

    LaunchedEffect(deckName, cardAdded?.value) {
        viewModel.updateDeckCategory(deckName)
        if (cardAdded?.value == true) {
            navController.currentBackStackEntry?.savedStateHandle?.set("cardAdded", false)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = stringResource(id = R.string.lbl_card_front) + ": " + card.front,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = stringResource(id = R.string.lbl_card_back) + ": " + card.back,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteCard(card.id) }
                                    ) {
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
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Button(
                onClick = onNavigateUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text(text = stringResource(id = R.string.lbl_bt_back))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onAddCard,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text(text = stringResource(id = R.string.lbl_bt_add_card))
            }
        }
    }
}
