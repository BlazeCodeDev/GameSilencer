// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import enums.GameStatus
import enums.Status
import kotlin.system.exitProcess

var isEnabled = mutableStateOf(true)
var useInGameCheck = mutableStateOf(true)
var sliderValue = mutableStateOf(20f)
var status = mutableStateOf("ic_unmuted.svg")
var time = mutableStateOf("0s")
var currentGame = mutableStateOf("Not ingame")

@Composable
@Preview
fun App() {
    val checkedState by isEnabled
    val newStatus by status
    val newTime by time
    val newCurrentGame by currentGame

    var sliderPosition by remember { mutableStateOf(20f) }
    var useInGameCheck by mutableStateOf(useInGameCheck)

    val timer = Timer

        MaterialTheme {
            Column (modifier = Modifier
                .background(Color(0xFF1e1b1e))
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box (contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Service Active", color = Color(0xFFe8e0e5))
                        Switch(checked = isEnabled.value,
                            onCheckedChange = { isEnabled.value = it },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = Color(0xFFf7d9ff),
                                checkedThumbColor = Color(0xFFffffff),
                                uncheckedTrackColor = Color(0xFF7d4996)
                            ))
                    }

                    Row(horizontalArrangement = Arrangement.End,
                        modifier = Modifier.align(Alignment.CenterEnd).padding(8.dp)){
                        Icon(painter = painterResource(newStatus),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp, 36.dp),
                            tint = Color(0xFFe8e0e5))
                    }
                }

                Row(modifier = Modifier.align(Alignment.End).padding(8.dp),){
                    Text(newTime, color = Color(0xFFe8e0e5))
                }

                Card (backgroundColor = Color(0xFF422c42),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(8.dp)){
                    Row (horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically){
                        Text(sliderPosition.toString().split(".")[0] + "s",
                            color = Color(0xFFe8e0e5),
                            modifier = Modifier.padding(8.dp)
                                .width(30.dp))
                        Slider(
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it; sliderValue.value = sliderPosition},
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

                Card (backgroundColor = Color(0xFF422c42),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(8.dp)
                        .fillMaxWidth()){
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text("Use Ingame check" ,
                            color = Color(0xFFe8e0e5),
                            modifier = Modifier.padding(8.dp))
                        Switch(checked = useInGameCheck.value,
                            onCheckedChange = { useInGameCheck.value = it },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = Color(0xFFf7d9ff),
                                checkedThumbColor = Color(0xFFffffff),
                                uncheckedTrackColor = Color(0xFF7d4996)
                            ))

                        Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
                            Row {
                                Text("Current state: ",
                                    color = Color(0xFFe8e0e5),
                                    modifier = Modifier.padding(8.dp))
                                Text(currentGame.value,
                                    color = Color(0xFFe8e0e5),
                                    modifier = Modifier.padding(8.dp))
                            }
                        }
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
        icon = painterResource("ic_icon.png")
    ) {
        val keyListener = KeyListener()
        keyListener.init()

        App()
    }
}

fun keyPressed() {
    val processListener = ProcessListener()
    val isIngame = processListener.isGame()
    changeGameStatus(isIngame)

    if((isEnabled.value && isIngame == GameStatus.INGAME && useInGameCheck.value) ||
            isEnabled.value && !useInGameCheck.value){
        val timer = Timer
        timer.startTimer(sliderValue.value.toInt())
    }
}

fun changeStatus(newStatus: Status){
    if(newStatus == Status.MUTED)
        status.value = "ic_muted.svg"
    else status.value = "ic_unmuted.svg"
}

fun changeGameStatus(newGameStatus: GameStatus) {
    if(newGameStatus == GameStatus.INGAME)
        currentGame.value = "Ingame" else
        currentGame.value = "Not ingame"
}

fun setTime(newTime: Long) {
    val timeTemp = newTime / 1000
    time.value = "$timeTemp s"
}

