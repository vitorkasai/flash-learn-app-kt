package com.example.flashlearn.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashlearn.R
import com.example.flashlearn.ui.theme.FlashCardApp_Theme

@Composable
fun Init(
    onStartButtonClicked: () -> Unit = {},
    onManageDecksButtonClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.lbl_welcome_msg),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(500.dp))
        Button(onClick = onStartButtonClicked) {
            Text(text = stringResource(id = R.string.lbl_bt_start))
        }
        Button(onClick = onManageDecksButtonClicked) {
            Text(text = stringResource(id = R.string.lbl_bt_score))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitPreview() {
    FlashCardApp_Theme {
        Init()
    }
}