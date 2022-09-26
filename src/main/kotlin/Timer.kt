import com.github.kwhat.jnativehook.GlobalScreen
import enums.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

object Timer {

    val audioManager = AudioManager()
    var countdown = 0L

    fun startTimer(){
        var currentMillis = System.currentTimeMillis()
        var timerMillis = currentMillis + 3000

        stopPlayback()

        GlobalScope.launch {
            while (!timerMillis.equals(0)) {
                if (timerMillis <= currentMillis) {
                    resumePlayback()
                    timerMillis = 0
                    break
                }
                currentMillis = System.currentTimeMillis()
                setTime(timerMillis - currentMillis)
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