import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent

class AudioManager {
    fun playPause(){
        GlobalScreen.postNativeEvent(NativeKeyEvent(2401, 0, 179, 57378, NativeKeyEvent.CHAR_UNDEFINED))
    }
}