package hu.bme.aut.android.pokemondb.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun Main() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Main.route) {
            composable(NavScreen.Main.route) {
                PokemonList(
                    viewModel = hiltViewModel(),
                    selectPokemon = {
                        navController.navigate("${NavScreen.Details.route}/$it")
                    }
                )
            }
            composable(
                route = NavScreen.Details.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.Details.argument0) { type = NavType.LongType }
                )
            ) { navBackStackEntry ->
                val pokemonId =
                    navBackStackEntry.arguments?.getLong(NavScreen.Details.argument0) ?: return@composable


            }
        }
    }
}

sealed class NavScreen(val route: String) {
    object Main : NavScreen("Main")

    object Details : NavScreen("Details") {
        const val routeWithArgument: String = "Details/{pokemonId}"
        const val argument0: String = "pokemonId"
    }
}