package hu.bme.aut.android.pokemondb.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.bme.aut.android.pokemondb.dto.PokemonDto

@Composable
fun UpdateDialog(
    toUpdate: PokemonDto,
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (PokemonDto) -> Unit
) {
    val nameText = remember { mutableStateOf(TextFieldValue(toUpdate.name!!)) }
    val typesText = remember { mutableStateOf(TextFieldValue(toUpdate.types!!)) }
    val weightText = remember { mutableStateOf(TextFieldValue(toUpdate.weight.toString())) }
    val heightText = remember { mutableStateOf(TextFieldValue(toUpdate.height.toString())) }
    val baseExpText = remember { mutableStateOf(TextFieldValue(toUpdate.baseExp.toString())) }
    val hpText = remember { mutableStateOf(TextFieldValue(toUpdate.hp.toString())) }
    val attackText = remember { mutableStateOf(TextFieldValue(toUpdate.attack.toString())) }
    val defenseText = remember { mutableStateOf(TextFieldValue(toUpdate.defense.toString())) }
    val speedText = remember { mutableStateOf(TextFieldValue(toUpdate.speed.toString())) }
    val specialAttackText = remember { mutableStateOf(TextFieldValue(toUpdate.specialAttack.toString())) }
    val specialDefenseText = remember { mutableStateOf(TextFieldValue(toUpdate.specialDefense.toString())) }

    Dialog(onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                TextFieldRow(nameText, "Name", KeyboardType.Ascii)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(typesText, "Types", KeyboardType.Ascii)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(weightText, "Weight", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(heightText, "Height", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(baseExpText, "Base EXP", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(hpText, "HP", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(attackText, "Attack", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(defenseText, "Defense", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(speedText, "Speed", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(specialAttackText, "Special Attack", KeyboardType.Number)
                Spacer(modifier = Modifier.height(5.dp))
                TextFieldRow(specialDefenseText, "Special Defense", KeyboardType.Number)

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, end = 4.dp)
                ) {
                    TextButton(
                        onClick = onNegativeClick,
                        modifier = Modifier.background(Color.White)
                    ) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(
                        onClick = {
                            val pokemon = PokemonDto(
                                id = toUpdate.id,
                                name = nameText.value.text,
                                types = typesText.value.text,
                                weight = Integer.parseInt(weightText.value.text),
                                height = Integer.parseInt(heightText.value.text),
                                baseExp = Integer.parseInt(baseExpText.value.text),
                                hp = Integer.parseInt(hpText.value.text),
                                attack = Integer.parseInt(attackText.value.text),
                                defense = Integer.parseInt(defenseText.value.text),
                                speed = Integer.parseInt(speedText.value.text),
                                specialAttack = Integer.parseInt(specialAttackText.value.text),
                                specialDefense = Integer.parseInt(specialDefenseText.value.text),
                                officialArtwork = toUpdate.officialArtwork
                            )

                            onPositiveClick(pokemon)
                        },
                        modifier = Modifier.background(Color.White)
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}