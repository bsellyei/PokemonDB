package hu.bme.aut.android.pokemondb.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.intellij.lang.annotations.JdkConstants

@Composable
fun SearchDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Boolean, String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val items = listOf(Pair("Generation I", "Generation II"), Pair("Generation III", "Generation IV"),
                   Pair("Generation V", "Generation VI"), Pair("Generation VII", "Generation VIII"))
    var state by remember { mutableStateOf("") }

    Dialog(onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextField(
                        modifier = Modifier.background(Color.White),
                        value = text,
                        onValueChange = { text = it },
                        label = { Text(text = "Search by ID or name") },
                        placeholder = { Text(text = "Search by ID or name") },
                        textStyle = TextStyle(color = Color.Black)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        items.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = state == item.first,
                                    onClick = { state = item.first }
                                )
                                Text(
                                    text = item.first,
                                    style = MaterialTheme.typography.body1
                                )

                                RadioButton(
                                    selected = state == item.second,
                                    onClick = { state = item.second }
                                )
                                Text(
                                    text = item.second,
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        }
                    }
                }

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
                            if (state.isEmpty()) {
                                onPositiveClick(false, text.text)
                            } else {
                                onPositiveClick(true, state)
                            }

                        },
                        modifier = Modifier.background(Color.White)
                    ) {
                        Text(text = "Search")
                    }
                }
            }
        }
    }
}