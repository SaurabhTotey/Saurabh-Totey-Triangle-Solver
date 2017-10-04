import net.miginfocom.swing.MigLayout               //http://www.miglayout.com                   is used: https://opensource.org/licenses/lgpl-3.0.html
import java.awt.Color
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
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
 * A globally accessible array of string formats of triangle parts
 * Is so a uniform and standard array and ordering may be used everywhere else
 */
val stringParts = arrayOf("a", "b", "c", "A", "B", "C")

/**
 * A class for the window that displays all of the information
 * Is how the user interfaces with the program (duh)
 * Triangle drawing is handled in TriangleDrawer.kt
 * The constructor of this object only contains the GUI layout and behaviour, which, due to the way Java Swing works, is basically unreadable
 */
class MainWindow{

    private lateinit var frame: JFrame
    private lateinit var defaultFont: Font
    private lateinit var titleFont: Font
    private lateinit var inputBoxes: Array<JTextField>
    private lateinit var typeBoxes: Array<JComboBox<String>>
    private val mathEngine = MathEvaluator()
    private val triangleDrawings: Array<TriangleDrawer> = arrayOf(TriangleDrawer(), TriangleDrawer())
    private var isInRads = true

    /**
     * What the main window should do once created
     * Makes and displays the window on the Swing thread
     * Defines graphics layout and behaviour
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
            frame.addComponentListener(object: ComponentAdapter() {
                override fun componentResized(e: ComponentEvent?) {
                    refresh()
                }
            })
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
            helpButton.icon = ImageIcon(ImageIO.read(File("res/HelpButton.png")).getScaledInstance(screenX / 90, screenX / 90, Image.SCALE_SMOOTH))
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
            ioPane.add(typeIO, "span, align center, wrap")
            //Below is where the user enters their data
            ioPane.add(JLabel(" "), "height 20%!, wrap")
            val boxWidth = 8
            inputBoxes = Array(3, {_ -> JTextField(boxWidth)}).map{it.font = defaultFont; it.horizontalAlignment = JTextField.CENTER; it.addKeyListener(object: KeyListener{
                override fun keyPressed(e: KeyEvent?) {}override fun keyTyped(e: KeyEvent?) {}
                override fun keyReleased(e: KeyEvent?) {
                    refresh()
                }
            }); it}.toTypedArray()
            typeBoxes = Array(3, {_ -> JComboBox(stringParts)}).map{it.font = defaultFont; it}.toTypedArray()
            var willChangeTypeIO = true //A lock that makes sure that if the typeIO box tries to change the typeboxes, the typeboxes won't change the typeIO box back
            typeBoxes.map{ //Mapped separately because it references type boxes, and if it was mapped in the above line, typeboxes doesn't exist yet as it is being constructed
                it.addActionListener{
                    if(willChangeTypeIO){ //Checks that the typebox is allowed to modify the typeIO box
                        var partsArray = Array(6, {_-> -1.0}) //Indexes 0..2 are sides, indexes 3..5 are angles
                        for(i in 0..2){
                            partsArray[stringParts.indexOf(typeBoxes[i].selectedItem as String)] = 1.0
                        }
                        typeIO.text = TriangleType(partsArray.sliceArray(0..2), partsArray.sliceArray(3..5)).toString()
                    }
                    refresh()
                }; it
            }
            typeIO.addKeyListener(object: KeyListener{
                override fun keyPressed(e: KeyEvent?){}
                /**
                 * Formats key inputs such that they are only A or S
                 */
                override fun keyTyped(e: KeyEvent?){
                    try{
                        if("as".contains(e?.keyChar!!.toLowerCase())){
                            e.keyChar = e.keyChar.toUpperCase()
                        }else e.consume()
                    }catch(er: Exception){}
                }
                /**
                 * Adjusts input triangle part boxes such that they match the given triangle type
                 */
                override fun keyReleased(e: KeyEvent?) {
                    if(typeIO.text.length > 3){
                        typeIO.text = typeIO.text.substring(0..2)
                    }
                    if(typeIO.text.length == 3){
                        willChangeTypeIO = false
                        if(typeIO.text[1] != typeIO.text[0] && typeIO.text[0] == typeIO.text[2]){   //If the middle char is unique
                            for(i in 0..2){
                                typeBoxes[i].selectedIndex = i + if(typeIO.text[i] == 'A') 3 else 0
                            }
                        }else{
                            var sideIndex = 0
                            var angleIndex = 3
                            for(i in 0..2){
                                typeBoxes[i].selectedIndex = if(typeIO.text[i] == 'S') sideIndex++ else angleIndex++
                            }
                        }
                        willChangeTypeIO = true
                    }
                }
            })
            for(i in 0..2){
                typeBoxes[i].selectedIndex = i
            }
            for(i in 0..2){
                ioPane.add(JLabel(), "pushx, growx") //Added before and after information so that it is spaced in center
                ioPane.add(inputBoxes[i], "growx")
                ioPane.add(typeBoxes[i], "growx")
                ioPane.add(JLabel(), "pushx, growx, wrap") //Added before and after information so that it is spaced in center
            }
            frame.add(ioPane, "dock east, height 90%!, width 25%!")

            frame.isVisible = true
            refresh()
        }
    }

    /**
     * What the window should do every time it needs to update or refresh itself
     * Updates the drawings
     */
    fun refresh(){
        var inputted: Triangle = try{
            var partsArray = Array(6, {_-> -1.0}) //Indexes 0..2 are sides, indexes 3..5 are angles
            for(i in 0..2){
                partsArray[stringParts.indexOf(typeBoxes[i].selectedItem as String)] = mathEngine.evaluate(inputBoxes[i].text)
            }
            Triangle(partsArray.sliceArray(0..2), partsArray.sliceArray(3..5))
        }catch(e: Exception){
            Triangle()
        }
        if(inputted.isValid()){
            val solutions = inputted.solutions()
            triangleDrawings[0].triangleToRepresent = solutions[0]
            if(solutions.size == 2){
                triangleDrawings[1].triangleToRepresent = solutions[1]
            }
        }
        for(drawing in triangleDrawings){
            frame.remove(drawing)
            if(drawing.triangleToRepresent != null){
                frame.add(drawing, "grow, push, wrap")
            }
        }
        frame.repaint()
        frame.revalidate()
    }

}