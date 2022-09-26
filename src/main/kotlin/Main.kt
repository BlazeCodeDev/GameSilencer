// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import enums.Status

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
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("isActive")
                    Switch(checked = isEnabled.value,
                        onCheckedChange = { isEnabled.value = it })

                    Text(newStatus)
                }
                Text(sliderPosition.toString().split(".")[0])
                Text(newTime)
                Row {

                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it; sliderValue.value = sliderPosition.toInt()},
                        valueRange = 1F..45F,
                        steps = 8)
                }
            }

        }
    }

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
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

