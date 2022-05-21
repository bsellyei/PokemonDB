package hu.bme.aut.android.pokemondb.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.coil.CoilImage
import hu.bme.aut.android.pokemondb.dto.PokemonDto

@Composable
fun PokemonDetails(
    pokemonId: Long,
    viewModel: DetailsViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = pokemonId) {
        viewModel.getPokemon(pokemonId)
    }

    val details: PokemonDto? by viewModel.pokemon.collectAsState(initial = null)
    details?.let { pokemon ->
        ConstraintLayout {
            val (body) = createRefs()
            Scaffold(
                backgroundColor = MaterialTheme.colors.primarySurface,
                topBar = { DetailsAppBar(pokemon.name!!, pressOnBack) },
                modifier = Modifier.constrainAs(body) {
                    top.linkTo(parent.top)
                }
            ) {
                PokemonDetailsBody(pokemon)
            }
        }
    }
}

@Composable
fun PokemonDetailsBody(
    pokemon: PokemonDto
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        item {
            CoilImage(
                imageModel = pokemon.officialArtwork,
                modifier = Modifier
                    .aspectRatio(1.0f)
                    .background(MaterialTheme.colors.onBackground),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Types:",
                    color = Color.White,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = pokemon.types!!,
                    color = Color.White,
                    style = MaterialTheme.typography.h6
                )
            }
        }

        val column1Weight = .5f // 30%
        val column2Weight = .5f
        item {
            Column() {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Basic stats:",
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start
                )

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Height", weight = column1Weight)
                    TableCell(text = pokemon.height.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Weight", weight = column1Weight)
                    TableCell(text = pokemon.weight.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Base XP", weight = column1Weight)
                    TableCell(text = pokemon.baseExp.toString(), weight = column2Weight)
                }
            }
        }

        item {
            Column() {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Combat stats:",
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start
                )

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "HP", weight = column1Weight)
                    TableCell(text = pokemon.hp.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Attack", weight = column1Weight)
                    TableCell(text = pokemon.attack.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Defense", weight = column1Weight)
                    TableCell(text = pokemon.defense.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Speed", weight = column1Weight)
                    TableCell(text = pokemon.speed.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Special Attack", weight = column1Weight)
                    TableCell(text = pokemon.specialAttack.toString(), weight = column2Weight)
                }

                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = "Special Defense", weight = column1Weight)
                    TableCell(text = pokemon.specialDefense.toString(), weight = column2Weight)
                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp),
        color = Color.White,
    )
}

@Composable
fun DetailsAppBar(
    title: String,
    pressOnBack: () -> Unit = {}
) {
    val appBarHorizontalPadding = 4.dp
    val titleIconModifier = Modifier.fillMaxHeight()
        .width(72.dp - appBarHorizontalPadding)

    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier.fillMaxWidth()) {

        //TopAppBar Content
        Box(Modifier.height(32.dp)) {

            Row(titleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    IconButton(
                        onClick = { pressOnBack() },
                        enabled = true,
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            }

            Row(Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically) {

                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = title
                        )
                    }
                }
            }
        }
    }
}
