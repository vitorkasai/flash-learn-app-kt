package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.flashlearn.R

@Composable
fun AddCardScreen(
    onCardAdded: (front: String, back: String) -> Unit,
    onNavigateUp: () -> Unit
) {
    var front by remember { mutableStateOf("") }
    var back by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.lbl_add_card_instruction),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = front,
            onValueChange = { front = it },
            label = { Text(text = stringResource(id = R.string.lbl_card_front)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = back,
            onValueChange = { back = it },
            label = { Text(text = stringResource(id = R.string.lbl_card_back)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (front.isNotBlank() && back.isNotBlank()) {
                    onCardAdded(front, back)
                    onNavigateUp()
                }
            }
        ) {
            Text(text = stringResource(id = R.string.lbl_bt_add_card))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateUp) {
            Text(text = stringResource(id = R.string.lbl_bt_back))
        }
    }
}
