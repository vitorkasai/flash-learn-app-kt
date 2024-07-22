package com.example.flashlearn.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashlearn.R
import com.example.flashlearn.ui.theme.FlashCardApp_Theme

@Composable
fun Game(
    revisionViewModel: RevisionViewModel = viewModel(),
    onNavigateUp: () -> Unit = {}
) {
    revisionViewModel.dialogTimeMessage = stringResource(id = R.string.dlg_time_msg)

    GameScreen(
        revisionViewModel.numbers,
        revisionViewModel.markers,
        onClick = { index, value -> revisionViewModel.click(index, value) }
    )
    if (revisionViewModel.showEndGameDialog) {
        EndGameDialog(
            dialogTitle = stringResource(id = R.string.dlg_title),
            dialogText = revisionViewModel.endGameDialogText,
            textFieldValue = revisionViewModel.playerName,
            onTextFieldValueChanged = { revisionViewModel.playerName = it },
            onConfirmation = {
                revisionViewModel.closeDialog()
                onNavigateUp()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    FlashCardApp_Theme {
        Game()
    }
}