package com.example.flashlearn.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashlearn.R
import com.example.flashlearn.ui.theme.FlashCardApp_Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val choiceDeckViewModel: ChoiceDeckViewModel = viewModel(factory = AppViewModelProvider.Factory)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "init",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("init") {
                Init(
                    onStartButtonClicked = { navController.navigate("display-decks") },
                    onManageDecksButtonClicked = { navController.navigate("manage-decks") }
                )
            }
            composable("display-decks") {
                ChoiceDeckScreen(
                    choiceDeckViewModel,
                    onNavigateUp = { navController.navigateUp() }
                )

            }
            composable("manage-decks") { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    FlashCardApp_Theme {
        App()
    }
}