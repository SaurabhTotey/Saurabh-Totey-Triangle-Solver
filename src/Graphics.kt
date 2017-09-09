import com.fathzer.soft.javaluator.DoubleEvaluator  //http://javaluator.sourceforge.net/en/home/ is used: https://opensource.org/licenses/lgpl-3.0.html
import net.miginfocom.swing.MigLayout               //http://www.miglayout.com                   is used: https://opensource.org/licenses/lgpl-3.0.html
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

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
 * Most of the graphics logic is defined here, but triangle drawing is handled in TriangleDrawer.kt
 */
class MainWindow{

    private lateinit var frame: JFrame
    private lateinit var defaultFont: Font
    private lateinit var titleFont: Font
    private val mathEngine = DoubleEvaluator()
    private val triangleDrawings: Array<TriangleDrawer> = arrayOf(TriangleDrawer(), TriangleDrawer())
    private var isInRads = true

    /**
     * What the main window should do once created
     * Makes and displays the window on the Swing thread
     * Defines graphics logic except for drawing triangles
     */
    init{
        SwingUtilities.invokeAndWait{
            //Makes the frame with the title and the icon
            frame = JFrame("Saurabh Totey Triangle Solver")
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

            //Makes the angles pane which handles angle mode and conversions of angles
            var anglesPane = JPanel(MigLayout(""))
            //Below makes the mode which selects whether the triangle inputs should be inputted/outputted as radians or degrees
            val angleOptions = arrayOf("Radians (Rad)", "Degrees (°)")
            var modeDropDownMenu = JComboBox(angleOptions)
            modeDropDownMenu.font = defaultFont
            modeDropDownMenu.addActionListener{actionEvent ->
                this.isInRads = (actionEvent.source as JComboBox<*>).selectedItem as String == angleOptions[0]
            }
            anglesPane.add(modeDropDownMenu)
            //Below makes the conversion boxes which will allow users to either enter in a radian value or a degrees value, and the other value will get updated to be equivalent
            var degreesConversionBox = JTextField()
            var radiansConversionBox = JTextField()
            degreesConversionBox.addKeyListener(object : KeyListener{

                override fun keyPressed(p0: KeyEvent?) {} override fun keyReleased(p0: KeyEvent?) {}

                override fun keyTyped(p0: KeyEvent?) {}

            })
            radiansConversionBox.addKeyListener(object : KeyListener{

                override fun keyPressed(p0: KeyEvent?) {} override fun keyReleased(p0: KeyEvent?) {}

                override fun keyTyped(p0: KeyEvent?) {}

            })
            frame.add(anglesPane, "dock south")

            frame.isVisible = true
        }
    }

    /**
     * What the window should do every time it needs to update or refresh itself
     */
    fun refresh(){
        frame.repaint()
        frame.revalidate()
    }

}