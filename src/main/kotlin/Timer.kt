import androidx.compose.runtime.mutableStateOf
import com.github.kwhat.jnativehook.GlobalScreen
import enums.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

object Timer {

    val audioManager = AudioManager()
    var countdown = 0L

    var timerRunning = false
    var timerMillis = mutableStateOf(0L)

    fun startTimer(time: Int){
        timerMillis = mutableStateOf(System.currentTimeMillis() + (time * 1000))     //*1000 to convert to Millis

        if(!timerRunning){
            stopPlayback()
            timerLogic()
            timerRunning = true
        }
    }

    fun timerLogic(){
        GlobalScope.launch {
            while (timerRunning) {
                var currentMillis = System.currentTimeMillis()
                if (timerMillis.value <= currentMillis) {
                    resumePlayback()
                    timerMillis.value = 0L
                    timerRunning = false
                    break
                }
                currentMillis = System.currentTimeMillis()
                setTime(timerMillis.value - currentMillis)
            }
        }
    }

    fun stopPlayback(){
        audioManager.playPause()
        changeStatus(Status.MUTED)
    }

    fun resumePlayback(){
        audioManager.playPause()
        changeStatus(Status.UNMUTED)
    }

}