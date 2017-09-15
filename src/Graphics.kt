import net.miginfocom.swing.MigLayout               //http://www.miglayout.com                   is used: https://opensource.org/licenses/lgpl-3.0.html
import java.awt.Color
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Image
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
    private val mathEngine = MathEvaluator()
    private val triangleDrawings: Array<TriangleDrawer> = arrayOf(TriangleDrawer(), TriangleDrawer())
    private var isInRads = true

    /**
     * What the main window should do once created
     * Makes and displays the window on the Swing thread
     * Defines graphics logic except for drawing triangles
     */
    init{
        SwingUtilities.invokeAndWait{
            /*
             * FRAME
             * Makes the frame with the title and the icon
             */
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
            titleFont = Font(Font.SERIF, Font.ROMAN_BASELINE, screenX / 90)
            defaultFont = Font(Font.MONOSPACED, Font.PLAIN, screenX / 130)

            /*
             * ANGLE PANE
             * Makes the angles pane which handles angle mode and conversions of angles
             */
            var anglesPane = JPanel(MigLayout())
            anglesPane.background = Color.GRAY
            anglesPane.border = BorderFactory.createEtchedBorder()
            //Below makes the mode which selects whether the triangle inputs should be inputted/outputted as radians or degrees
            var angleModeLabel = JLabel("Angle Input Mode:")
            angleModeLabel.font = defaultFont
            anglesPane.add(angleModeLabel)
            val angleOptions = arrayOf("Radians (Rad)", "Degrees (°)")
            var modeDropDownMenu = JComboBox(angleOptions)
            modeDropDownMenu.font = defaultFont
            modeDropDownMenu.addActionListener{actionEvent -> this.isInRads = (actionEvent.source as JComboBox<*>).selectedItem as String == angleOptions[0]}
            anglesPane.add(modeDropDownMenu, "push")
            //Below makes the conversion boxes which will allow users to either enter in a radian value or a degrees value, and the other value will get updated to be equivalent
            val boxLength = 8
            var degreesConversionBox = JTextField(boxLength)
            var radiansConversionBox = JTextField(boxLength)
            degreesConversionBox.addKeyListener(object: KeyListener{
                override fun keyTyped(p0: KeyEvent?) {} override fun keyPressed(p0: KeyEvent?) {}
                override fun keyReleased(p0: KeyEvent?) {
                    radiansConversionBox.text = try{
                        (mathEngine.evaluate(degreesConversionBox.text) * Math.PI / 180).toString()
                    }catch(e: Exception){
                        ""
                    }
                    radiansConversionBox.caretPosition = 0
                }
            })
            radiansConversionBox.addKeyListener(object: KeyListener{
                override fun keyTyped(p0: KeyEvent?) {} override fun keyPressed(p0: KeyEvent?) {}
                override fun keyReleased(p0: KeyEvent?) {
                    degreesConversionBox.text = try{
                        (mathEngine.evaluate(radiansConversionBox.text) * 180 / Math.PI).toString()
                    }catch(e: Exception){
                        ""
                    }
                    degreesConversionBox.caretPosition = 0
                }
            })
            degreesConversionBox.font = defaultFont
            radiansConversionBox.font = defaultFont
            var degreesLabel = JLabel("°")
            var radiansLabel = JLabel("Rad")
            var equalsLabel = JLabel(" = ")
            degreesLabel.font = defaultFont
            radiansLabel.font = defaultFont
            equalsLabel.font = defaultFont
            anglesPane.add(degreesConversionBox, "")
            anglesPane.add(degreesLabel, "")
            anglesPane.add(equalsLabel, "")
            anglesPane.add(radiansConversionBox, "")
            anglesPane.add(radiansLabel, "push")
            var helpButton = JButton()
            helpButton.icon = ImageIcon(ImageIO.read(File("res/HelpButton.png")).getScaledInstance(25, 25, Image.SCALE_SMOOTH)) //TODO find a better way to have image be reasonable size rather than hardcoding 25px
            anglesPane.add(helpButton)
            frame.add(anglesPane, "dock south, height 10%!, width 100%!")

            /*
             * IO PANE
             * Where the user can input their triangle information and where a little bit of triangle information is displayed
             */
            var ioPane = JPanel(MigLayout("fillx"))
            ioPane.background = Color.LIGHT_GRAY
            ioPane.border = BorderFactory.createEtchedBorder()
            var paneLabel = JLabel("Triangle Information")
            paneLabel.font = titleFont
            ioPane.add(paneLabel, "span, align center, wrap")
            ioPane.add(JLabel(" "), "span, wrap") //Just for space
            var typeLabel = JLabel("Triangle Type")
            typeLabel.font = defaultFont
            ioPane.add(typeLabel, "span, align center, wrap")
            var typeIO = JTextField(13)
            typeIO.horizontalAlignment = JTextField.CENTER
            typeIO.font = defaultFont
            typeIO.addKeyListener(object: KeyListener{
                override fun keyTyped(e: KeyEvent?){} override fun keyPressed(e: KeyEvent?){}
                override fun keyReleased(e: KeyEvent?) {
                    if(typeIO.text.length == 3){
                        //TODO
                    }
                }
            })
            ioPane.add(typeIO, "span, align center, wrap")
            frame.add(ioPane, "dock east, height 90%!, width 25%!")

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