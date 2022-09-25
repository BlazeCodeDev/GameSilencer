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
var status = mutableStateOf("Unmuted")

@Composable
@Preview
fun App() {
    val checkedState by isEnabled
    val newStatus by status

    var sliderPosition by remember { mutableStateOf(20f) }

    val timer = Timer()

        MaterialTheme {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("isActive")
                    Switch(checked = isEnabled.value,
                        onCheckedChange = { isEnabled.value = it })

                    Text(newStatus)
                }
                Text(sliderPosition.toString().split(".")[0])
                Row {

                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it},
                        valueRange = 0F..45F,
                        steps = 15)
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
    if(isEnabled.value && Status.valueOf(status.value.uppercase()) == Status.UNMUTED){
        val timer = Timer()
        timer.startTimer()
    }
}

fun changeStatus(newStatus: Status){
    if(newStatus == Status.MUTED)
        status.value = "Muted"
    else status.value = "Unmuted"
}

