package com.example.flashlearn.compose

import CardsRevisionScreen
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashlearn.R
import com.example.flashlearn.repository.model.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val choiceDeckViewModel: ChoiceDeckViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val manageDecksViewModel: ManageDecksViewModel =
        viewModel(factory = AppViewModelProvider.Factory)
    val addDeckViewModel: AddDeckViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val addCardViewModel: AddCardViewModel = viewModel(factory = AppViewModelProvider.Factory)

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
                    onStartButtonClicked = { navController.navigate("choice-deck") },
                    onManageDecksButtonClicked = { navController.navigate("manage-decks") }
                )
            }
            composable("choice-deck") {
                ChoiceDeckScreen(
                    choiceDeckViewModel,
                    onNavigateUp = {
                        navController.navigate("init") {
                            popUpTo("init") { inclusive = true }
                        }
                    },
                    onDeckSelected = { cards ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("cards", cards)
                        navController.navigate("cards-revision")
                    }
                )
            }
            composable("cards-revision") {
                val cards =
                    navController.previousBackStackEntry?.savedStateHandle?.get<List<Card>>("cards")
                cards?.let {
                    CardsRevisionScreen(
                        cards = it,
                        onNavigateUp = { navController.navigateUp() },
                        navController = navController
                    )
                }
            }
            composable("end-revision") {
                EndRevisionScreen(
                    onBackToDecks = { navController.navigate("choice-deck") }
                )
            }
            composable("manage-decks") {
                ManageDecksScreen(
                    manageDecksViewModel,
                    onNavigateUp = {
                        navController.navigate("init") {
                            popUpTo("init") {
                                inclusive = true
                            }
                        }
                    },
                    onAddDeck = {
                        navController.navigate("add-deck")
                    },
                    onDeckSelected = { deckName, cards ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "deckName",
                            deckName
                        )
                        navController.currentBackStackEntry?.savedStateHandle?.set("cards", cards)
                        navController.navigate("deck-detail")
                    }
                )
            }
            composable("add-deck") {
                AddDeckScreen(
                    addDeckViewModel,
                    onNavigateBack = { navController.navigateUp() },
                    onDeckAdded = {
                        navController.popBackStack()
                    }
                )
            }
            composable("deck-detail") {
                val deckName =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("deckName")
                val cards =
                    navController.previousBackStackEntry?.savedStateHandle?.get<List<Card>>("cards")
                deckName?.let { name ->
                    cards?.let {
                        DeckDetailScreen(
                            deckName = name,
                            cards = it,
                            onNavigateUp = { navController.navigateUp() },
                            onAddCard = {
                                navController.navigate("add-card/$name")
                            }
                        )
                    }
                }
            }
            composable(
                route = "add-card/{category}",
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                AddCardScreen(
                    category = category,
                    onCardAdded = { front, back ->
                        addCardViewModel.addCard(
                            category,
                            front,
                            back
                        )
                    },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}