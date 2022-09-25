// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.kwhat.jnativehook.GlobalScreen
import javax.swing.GroupLayout

@Composable
@Preview
fun App() {
    val checkedState = remember { mutableStateOf(false) }

    MaterialTheme {
        Row (verticalAlignment = Alignment.CenterVertically){
            Text("isActive")
            Switch(checked = checkedState.value,
                onCheckedChange = {checkedState.value = it})
        }

    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
    ){
        val keyListener = KeyListener()
        keyListener.init()

        App()
    }
}

