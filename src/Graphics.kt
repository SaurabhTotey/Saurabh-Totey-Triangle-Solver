import net.miginfocom.swing.MigLayout
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

/**
 * Entry point of the program
 * Would have made a separate file, but it seems to make sense that where the code actually runs is in Graphics
 * Triangle.kt only defines an object which Graphics uses, so the actual code that runs all happens in this file
 */
fun main(args: Array<String>) {
    MainWindow()
}

/**
 * A class for the window that displays all of the information
 * Is how the user interfaces with the program (duh)
 */
class MainWindow{

    private lateinit var frame: JFrame
    private lateinit var defaultFont: Font
    private lateinit var titleFont: Font
    private val triangleDrawings: Array<TriangleDrawer> = arrayOf(TriangleDrawer(), TriangleDrawer())

    init{
        SwingUtilities.invokeAndWait{
            //Makes the frame with the title and the icon
            frame = JFrame("Totey Triangle Solver")
            frame.iconImage = ImageIO.read(File("res/Icon.png"))
            frame.layout = MigLayout()

            frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE

            //Makes the frame bounds such that the frame is exactly in the middle of the screen filling half of its width/height
            val allDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices
            val topLeftX = allDevices[0].defaultConfiguration.bounds.x
            val topLeftY = allDevices[0].defaultConfiguration.bounds.y
            val screenX = allDevices[0].defaultConfiguration.bounds.width
            val screenY = allDevices[0].defaultConfiguration.bounds.height
            frame.setBounds(screenX / 4 + topLeftX, screenY / 4 + topLeftY, screenX / 2, screenY / 2)

            //Makes the font based off of screen size
            titleFont = Font(Font.SERIF, Font.ROMAN_BASELINE, screenX / 70)
            defaultFont = Font(Font.MONOSPACED, Font.PLAIN, screenX / 130)

            frame.isVisible = true
        }
    }

}