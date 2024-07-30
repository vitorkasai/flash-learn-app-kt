package com.example.flashlearn.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddCardScreen(
    category: String,
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
        Button(onClick = onNavigateUp) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = front,
            onValueChange = { front = it },
            label = { Text("Front") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = back,
            onValueChange = { back = it },
            label = { Text("Back") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (front.isNotBlank() && back.isNotBlank()) {
                    onCardAdded(front, back) // Chamar a função passando front e back
                    onNavigateUp()
                }
            }
        ) {
            Text(text = "Add Card")
        }
    }
}
