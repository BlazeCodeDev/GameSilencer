// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import enums.Status
import kotlin.system.exitProcess

var isEnabled = mutableStateOf(false)
var sliderValue = mutableStateOf(0)
var status = mutableStateOf("Unmuted")
var time = mutableStateOf("0 ms")

@Composable
@Preview
fun App() {
    val checkedState by isEnabled
    val newStatus by status
    val newTime by time

    var sliderPosition by remember { mutableStateOf(20f) }

    val timer = Timer

        MaterialTheme {
            Column (modifier = Modifier
                .background(Color(0xFF1e1b1e))
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Service Active", color = Color(0xFFe8e0e5))
                    Switch(checked = isEnabled.value,
                        onCheckedChange = { isEnabled.value = it },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Color(0xFFf7d9ff),
                            checkedThumbColor = Color(0xFFffffff),
                            uncheckedTrackColor = Color(0xFF7d4996)
                        ))

                    Text(newStatus, color = Color(0xFFe8e0e5))
                }

                Text(newTime)
                Card (backgroundColor = Color(0xFF422c42),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(8.dp)){
                    Row (horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically){
                        Text(sliderPosition.toString().split(".")[0] + "s",
                            color = Color(0xFFe8e0e5),
                            modifier = Modifier.padding(8.dp))
                        Slider(
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it; sliderValue.value = sliderPosition.toInt()},
                            valueRange = 1F..45F,
                            steps = 8,
                            colors = SliderDefaults.colors(
                                thumbColor = Color(0xFFffffff),
                                activeTrackColor = Color(0xFFf7d9ff),
                                inactiveTrackColor = Color(0xFF310048)
                            )
                        )
                    }
                }
            }
        }
    }

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 500.dp, height = 300.dp),
        title = "GameSilencer",
    ) {
        val keyListener = KeyListener()
        keyListener.init()

        App()
    }
}

fun keyPressed() {
    if(isEnabled.value){
        val timer = Timer
        timer.startTimer(sliderValue.value)
    }
}

fun changeStatus(newStatus: Status){
    if(newStatus == Status.MUTED)
        status.value = "Muted"
    else status.value = "Unmuted"
}

fun setTime(newTime: Long) {
    time.value = "$newTime ms"
}

