package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import com.example.flashlearn.R
import com.example.flashlearn.repository.model.Card

@Composable
fun CardsRevisionScreen(
    cards: List<Card>,
    onNavigateUp: () -> Unit = {},
    navController: NavController
) {
    var currentCardIndex by remember { mutableIntStateOf(0) }
    var isBackVisible by remember { mutableStateOf(false) }
    var isNextEnabled by remember { mutableStateOf(false) }

    if (currentCardIndex >= cards.size) {
        navController.navigate("end-revision")
        return
    }

    val currentCard = cards[currentCardIndex]

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Frente: ${currentCard.front}",
                style = MaterialTheme.typography.headlineMedium
            )
            if (isBackVisible) {
                Text(
                    text = "Verso: ${currentCard.back}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {
                    isBackVisible = !isBackVisible
                    isNextEnabled = true
                }
            ) {
                Text(text = stringResource(id = R.string.lbl_bt_flip))
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {
                    if (currentCardIndex < cards.size - 1) {
                        currentCardIndex++
                        isBackVisible = false
                        isNextEnabled = false
                    } else {
                        navController.navigate("end-revision")
                    }
                },
                enabled = isNextEnabled
            ) {
                Text(text = stringResource(id = R.string.lbl_bt_next_card))
            }
        }

        Button(
            onClick = onNavigateUp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = stringResource(id = R.string.lbl_bt_quit))
        }
    }
}