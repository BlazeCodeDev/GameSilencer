
import com.sun.jna.Native
import com.sun.jna.platform.win32.User32


class ProcessListener {

    fun getActiveWindowTitle(): String {
        val fgWindow = User32.INSTANCE.GetForegroundWindow()
        val titleLength = User32.INSTANCE.GetWindowTextLength(fgWindow) + 1
        val title = CharArray(titleLength)
        User32.INSTANCE.GetWindowText(fgWindow, title, titleLength)
        return Native.toString(title)
    }

    // GAME TITLES:
    // "Rainbow Six"
    // "Counter-Strike: Global Offensive - Direct3D 9"

    fun isGame(): Boolean{
        when (getActiveWindowTitle()) {
            "GameSilencer" -> return true     // FOR TESTING PURPOSES
            "Rainbow Six" -> return true
            "Counter-Strike: Global Offensive - Direct3D 9" -> return true
            else -> return false
        }
    }
}