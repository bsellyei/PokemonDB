package hu.bme.aut.android.pokemondb.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.skydoves.landscapist.coil.CoilImage
import hu.bme.aut.android.pokemondb.R
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import hu.bme.aut.android.pokemondb.ui.custom.StaggeredVerticalGrid
import hu.bme.aut.android.pokemondb.ui.dialogs.AddNewDialog

@Composable
fun PokemonList(
    viewModel: MainViewModel,
    selectPokemon: (Long) -> Unit
) {
    val pokemons by viewModel.pokemons.observeAsState(initial = emptyList())
    val showSearch = remember { mutableStateOf(false) }
    val showAddNew = remember { mutableStateOf(false) }

    ConstraintLayout {
        val (body) = createRefs()
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { MainAppBar(showSearch, showAddNew) },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            PokemonGrid(modifier, pokemons, selectPokemon)

            if (showSearch.value) {
                SearchDialog(
                    onDismiss = {
                        showSearch.value = !showSearch.value
                    }, onNegativeClick = {
                        showSearch.value = !showSearch.value
                    }, onPositiveClick = { searchType, text ->
                        showSearch.value = !showSearch.value
                        viewModel.search(searchType, text)
                    }
                )
            }

            if (showAddNew.value) {
                AddNewDialog(
                    onDismiss = {
                        showAddNew.value = !showAddNew.value
                    }, onNegativeClick = {
                        showAddNew.value = !showAddNew.value
                    }, onPositiveClick = { pokemon ->
                        showAddNew.value = !showAddNew.value
                        viewModel.add(pokemon)
                    }
                )
            }
        }
    }
}

@Composable
private fun PokemonGrid(
    modifier: Modifier = Modifier,
    pokemons: List<PokemonDto>,
    selectPokemon: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.primary)
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            pokemons.forEach { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    selectPokemon = selectPokemon
                )
            }
        }
    }
}

@Composable
private fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: PokemonDto,
    selectPokemon: (Long) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectPokemon(pokemon.id!!) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title) = createRefs()
            CoilImage(
                imageModel = pokemon.officialArtwork,
                modifier = Modifier
                    .aspectRatio(1.0f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp),
                text = pokemon.name!!,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MainAppBar(
    showSearch: MutableState<Boolean>,
    showAddNew: MutableState<Boolean>
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
        )

        IconButton(onClick = {
            showSearch.value = true
        }) {
            Icon(Icons.Filled.Search, "Search")
        }

        IconButton(onClick = {
            showAddNew.value = true
        }) {
            Icon(Icons.Filled.Add, "Add")
        }
    }
}