package com.example.flashlearn.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashlearn.R

@Composable
fun AddDeckScreen(
    addDeckViewModel: AddDeckViewModel = viewModel(),
    onNavigateBack: () -> Unit = {},
    onDeckAdded: () -> Unit
) {
    var category by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Button(onClick = onNavigateBack) {
                Text(text = stringResource(id = R.string.lbl_bt_back))
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
        Text(
            text = stringResource(id = R.string.lbl_add_deck),
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text(stringResource(id = R.string.lbl_category)) },
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = {
            addDeckViewModel.addDeck(category)
            onDeckAdded()
        }) {
            Text(text = stringResource(id = R.string.lbl_bt_add))
        }
    }
}
