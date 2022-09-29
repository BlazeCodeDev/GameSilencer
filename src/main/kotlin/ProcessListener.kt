
import com.sun.jna.Native
import com.sun.jna.platform.win32.User32
import enums.GameStatus


class ProcessListener {

    private fun getActiveWindowTitle(): String {
        val fgWindow = User32.INSTANCE.GetForegroundWindow()
        val titleLength = User32.INSTANCE.GetWindowTextLength(fgWindow) + 1
        val title = CharArray(titleLength)
        User32.INSTANCE.GetWindowText(fgWindow, title, titleLength)
        return Native.toString(title)
    }

    // GAME TITLES:
    // "Rainbow Six"
    // "Counter-Strike: Global Offensive - Direct3D 9"

    fun isGame(): GameStatus{
        when (getActiveWindowTitle()) {
            "GameSilencer" -> return GameStatus.INGAME     // FOR TESTING PURPOSES
            "Rainbow Six" -> return GameStatus.INGAME
            "Counter-Strike: Global Offensive - Direct3D 9" -> return GameStatus.INGAME
            else -> return GameStatus.NOT_INGAME
        }
    }
}