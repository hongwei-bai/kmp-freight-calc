package com.example.freightcalc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    var lengthText by remember { mutableStateOf("5") }
    var heightText by remember { mutableStateOf("5") }
    var depthText by remember { mutableStateOf("5") }

    // Parse input text to int safely
    val length = lengthText.toIntOrNull() ?: 0
    val height = heightText.toIntOrNull() ?: 0
    val depth = depthText.toIntOrNull() ?: 0

    val volumeWeight = remember(length, height, depth) {
        if (length > 0 && height > 0 && depth > 0) {
            (length * height * depth / 5000.0)
        } else 0.0
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.weight(0.2f))
            Text(
                text = "国际货运体积重计算器",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                listOf(
                    Triple("长度", lengthText) { new: String -> lengthText = new },
                    Triple("高度", heightText) { new: String -> heightText = new },
                    Triple("深度", depthText) { new: String -> depthText = new },
                ).forEach { (label, text, onTextChange) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = label)

                        Spacer(modifier = Modifier.height(6.dp))

                        Button(
                            onClick = {
                                val current = text.toIntOrNull() ?: 0
                                onTextChange((current + 1).toString())
                            },
                            modifier = Modifier.size(36.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("▲", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        OutlinedTextField(
                            value = text,
                            onValueChange = { new ->
                                if (new.all { it.isDigit() } || new.isEmpty()) {
                                    onTextChange(new)
                                }
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Text(
                            text = "厘米",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Button(
                            onClick = {
                                val current = text.toIntOrNull() ?: 0
                                onTextChange((current - 1).coerceAtLeast(0).toString())
                            },
                            modifier = Modifier.size(36.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("▼", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "体积重：${"%.2f".format(volumeWeight)} 公斤",
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
