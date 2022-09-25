import com.github.kwhat.jnativehook.GlobalScreen
import enums.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Timer {

    fun startTimer(){
        GlobalScope.launch {
            val audioManager = AudioManager()
            audioManager.playPause()
            changeStatus(Status.MUTED)

            println("1st")

            delay(2000)

            println("2nd")

            audioManager.playPause()
            changeStatus(Status.UNMUTED)
        }
    }
}