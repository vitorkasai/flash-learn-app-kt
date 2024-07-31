package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.flashlearn.R

@Composable
fun EndRevisionScreen(
    onBackToDecks: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = stringResource(id = R.string.end_revision),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = onBackToDecks) {
            Text(text = stringResource(id = R.string.return_to_decks))
        }
    }
}