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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.bme.aut.android.pokemondb.dto.PokemonDto

@Composable
fun AddNewDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (PokemonDto) -> Unit
) {
    val nameText = remember { mutableStateOf(TextFieldValue("")) }
    val typesText = remember { mutableStateOf(TextFieldValue("")) }
    val weightText = remember { mutableStateOf(TextFieldValue("")) }
    val heightText = remember { mutableStateOf(TextFieldValue("")) }
    val baseExpText = remember { mutableStateOf(TextFieldValue("")) }
    val hpText = remember { mutableStateOf(TextFieldValue("")) }
    val attackText = remember { mutableStateOf(TextFieldValue("")) }
    val defenseText = remember { mutableStateOf(TextFieldValue("")) }
    val speedText = remember { mutableStateOf(TextFieldValue("")) }
    val specialAttackText = remember { mutableStateOf(TextFieldValue("")) }
    val specialDefenseText = remember { mutableStateOf(TextFieldValue("")) }

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
                                id = 0,
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
                                officialArtwork = ""
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

@Composable
fun TextFieldRow(
    textFieldValue: MutableState<TextFieldValue>,
    label: String,
    keyboardType: KeyboardType
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        TextField(
            modifier = Modifier.background(Color.White)
                .height(50.dp),
            value = textFieldValue.value,
            onValueChange = { textFieldValue.value = it },
            label = { Text(text = label) },
            placeholder = { Text(text = label) },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}
