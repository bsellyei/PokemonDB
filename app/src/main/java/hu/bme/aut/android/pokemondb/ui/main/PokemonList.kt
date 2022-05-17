package hu.bme.aut.android.pokemondb.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun PokemonList(
    viewModel: MainViewModel,
    selectPokemon: (Long) -> Unit
) {
    val pokemons: List<PokemonDto> by viewModel.pokemons.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading

    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { MainAppBar() },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            PokemonGrid(modifier, pokemons, selectPokemon)
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(progress) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
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
            .background(MaterialTheme.colors.background)
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
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop,
                failure = {
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "image request failed",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp),
                text = pokemon.name!!,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MainAppBar() {
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
    }
}