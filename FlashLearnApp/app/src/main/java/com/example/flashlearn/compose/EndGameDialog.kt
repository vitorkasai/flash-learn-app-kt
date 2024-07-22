package com.example.flashlearn.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.flashlearn.R
import com.example.flashlearn.ui.theme.FlashCardApp_Theme

@Composable
fun EndGameDialog(
    dialogTitle: String,
    dialogText: String,
    textFieldValue: String,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    onTextFieldValueChanged: (String) -> Unit = {}
) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Default.Info,
                contentDescription = "Example Icon"
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column {
                Text(text = dialogText)
                TextField(
                    value = textFieldValue,
                    singleLine = true,
                    onValueChange = { onTextFieldValueChanged(it) }
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.dlg_ok))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EndGameDialogPreview() {
    FlashCardApp_Theme {
        EndGameDialog(
            dialogTitle = "Alert dialog example",
            dialogText = "This is an example of an alert dialog with buttons.",
            textFieldValue = "andre"
        )
    }
}