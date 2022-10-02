import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent.VC_MEDIA_PLAY

class AudioManager {
    fun playPause(){
        GlobalScreen.postNativeEvent(NativeKeyEvent(2401, 0, 179, VC_MEDIA_PLAY , NativeKeyEvent.CHAR_UNDEFINED))
    }
}