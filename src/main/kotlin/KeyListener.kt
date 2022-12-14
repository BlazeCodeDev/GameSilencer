import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeInputEvent.ALT_MASK
import com.github.kwhat.jnativehook.NativeInputEvent.SHIFT_MASK
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener

class KeyListener : NativeKeyListener {

    fun init(){
        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeKeyListener(this)
    }

    override fun nativeKeyPressed(key : NativeKeyEvent){
        println(com.github.kwhat.jnativehook.keyboard.NativeKeyEvent.getKeyText(key.keyCode))

        val currentChar = NativeKeyEvent.getKeyText(key.keyCode)
        val isCurrentCharWASD = currentChar.equals("W") ||
                                currentChar.equals("A") ||
                                currentChar.equals("S") ||
                                currentChar.equals("D")

        if((isShiftDown(key) || isALtDown(key)) && isCurrentCharWASD){
            println("Ingame")
            keyPressed()
        }
    }

    private fun isShiftDown(e: NativeKeyEvent): Boolean {
        return e.modifiers and SHIFT_MASK != 0
    }

    private fun isALtDown(e: NativeKeyEvent): Boolean {
        return e.modifiers and ALT_MASK != 0
    }
}