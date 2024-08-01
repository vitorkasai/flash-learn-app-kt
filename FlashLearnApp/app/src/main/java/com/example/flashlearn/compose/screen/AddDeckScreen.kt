package com.example.flashlearn.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashlearn.R
import com.example.flashlearn.compose.viewmodel.AddDeckViewModel

@Composable
fun AddDeckScreen(
    addDeckViewModel: AddDeckViewModel = viewModel(),
    onNavigateBack: () -> Unit = {},
    onDeckAdded: () -> Unit
) {
    var category by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.lbl_add_deck),
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text(stringResource(id = R.string.lbl_category)) },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                addDeckViewModel.addDeck(category)
                onDeckAdded()
            }) {
                Text(text = stringResource(id = R.string.lbl_bt_add))
            }
            Button(onClick = onNavigateBack) {
                Text(text = stringResource(id = R.string.lbl_bt_back))
            }
        }
    }
}
