import com.github.kwhat.jnativehook.GlobalScreen
import enums.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

object Timer {

    val audioManager = AudioManager()
    var countdown = 0L

    fun startTimer(){
        if(countdown == 0L){
            stopPlayback()
        }
        countdown = 3L

        GlobalScope.launch {
            while (countdown > 0L){
                delay(1000)
                countdown - 1
            }
            println(countdown.toString())
            if(countdown == 0L) resumePlayback()
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