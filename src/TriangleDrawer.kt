import java.awt.*
import javax.swing.JPanel

/**
 * The class that extends JPanel that has a drawn triangle on it
 * As long as it is supplied with a solved triangle, it will have the triangle drawn on it
 */
class TriangleDrawer: JPanel(){

    /**
     * The color pallete of the colors to be used for drawing the triangle
     */
    val aColor = Color.BLUE
    val bColor = Color.RED
    val cColor = Color.ORANGE
    //Sets each index to a color
    val colorMap = hashMapOf(0 to aColor, 1 to bColor, 2 to cColor)
    var isRadians = true

    /**
     * The triangle that the panel will draw
     * Will only accept solved triangles
     */
    var triangleToRepresent: Triangle? = null
        set(newTriangle){
            field = if(newTriangle == null || !newTriangle.isSolved) null else newTriangle
        }

    /**
     * Where the triangle actually gets drawn
     * Method defines how triangle is drawn
     */
    override fun paint(graphics: Graphics?) {

        //Boilerplate stuff that needs to happen; super paint method is called, triangle is checked for validity, and a graphics2d object is stored
        super.paint(graphics)
        if(triangleToRepresent == null || !triangleToRepresent!!.isSolved) return
        val g = graphics!!.create() as Graphics2D

        //Below bubble-sorts (not the most efficient sort, but whatever) the sides and angles and keeps the new order of the indices
        var sides = triangleToRepresent!!.sides.clone()
        var angles = triangleToRepresent!!.angles.clone()
        var indices = (0..2).toList().toTypedArray()
        for(i in 2 downTo 0){
            for(j in 1..i){
                if(sides[j - 1] > sides[j]){
                    sides[j] = sides[j - 1].also{sides[j - 1] = sides[j]} //Swaps the sides
                    angles[j] = angles[j - 1].also{angles[j - 1] = angles[j]} //Swaps the angles
                    indices[j] = indices[j - 1].also{indices[j - 1] = indices[j]} //Swaps the indices
                }
            }
        }

        //Define some constants that are useful for scaling and centering the triangle drawing
        val triangleWidth = if(this.height < this.width) this.height else this.width - 10 //Is the triangle width because the largest side is always at the bottom and is scaled to this value
        val triangleHeight = (sides[1] * Math.sin(angles[0]) * triangleWidth / sides[2]).toInt()
        val leftX = (this.width - triangleWidth) / 2
        val rightX = leftX + triangleWidth
        val bottomY = (this.height - triangleHeight) / 2 + triangleHeight
        val meetingX = leftX + (Math.cos(angles[1]) * sides[0] * triangleWidth / sides[2]).toInt()
        val meetingY = bottomY - triangleHeight

        //Sets the line thickness so that it scales with the available space
        g.stroke = BasicStroke((triangleWidth / 200).toFloat(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)

        //Actually draws the triangle
        g.color = colorMap[indices[2]]
        g.drawLine(leftX, bottomY, rightX, bottomY) //Largest side is always horizontally oriented at the bottom
        g.color = colorMap[indices[0]]
        g.drawLine(leftX, bottomY, meetingX, meetingY) //Smallest side is always the left leg of the triangle
        g.color = colorMap[indices[1]]
        g.drawLine(rightX, bottomY, meetingX, meetingY) //Medium side is always the right leg of the triangle

        //Draws black dots on all the line intersections so the triangle doesn't look ugly
        g.color = Color.BLACK
        g.drawLine(leftX, bottomY, leftX, bottomY)
        g.drawLine(rightX, bottomY, rightX, bottomY)
        g.drawLine(meetingX, meetingY, meetingX, meetingY)

        //Below draws text that displays triangle information
        g.font = Font(Font.MONOSPACED, Font.PLAIN, triangleWidth / 35)
        val stringParts = arrayOf("a", "b", "c", "A", "B", "C")
        val truncateLength = 5
        val lineSpacing = 2 * triangleWidth / 35
        fun formatString(toFormat: Double): String{
            return when {
                toFormat.toString().endsWith("0") && !toFormat.toString().endsWith(".0") -> formatString(toFormat.toString().substring(0 until toFormat.toString().length).toDouble())
                toFormat.toString().length >= truncateLength + 1 -> { //TODO this will infinitely run if the number of non-decimal digits in the number is larger than truncateLength + 1
                    if(toFormat.toString().length >= truncateLength + 2 && toFormat.toString()[truncateLength + 1].toInt() >= 5){
                        formatString((toFormat + Math.pow(10.0, (-truncateLength).toDouble())).toString().substring(0..truncateLength).toDouble())
                    }else{
                        toFormat.toString().substring(0..truncateLength)
                    }
                }
                else -> toFormat.toString() + " ".repeat(truncateLength + 1 - toFormat.toString().length)
            }
        }
        for(i in 0..2){
            g.color = colorMap[i]
            g.drawString(stringParts[i] + " = " + formatString(triangleToRepresent!!.sides[i]) + " " + stringParts[i + 3] + " = " + if(isRadians){formatString(triangleToRepresent!!.angles[i])}else{formatString(asDegrees(triangleToRepresent!!.angles[i]))}, lineSpacing / 2, (i + 1) * lineSpacing)
        }
        g.color = Color.BLACK
        g.drawString(" ".repeat((19 - 12) / 2) + "Area = " + formatString(triangleToRepresent!!.area()), lineSpacing / 2, 4 * lineSpacing) //19 is the length of the above strings, 12 is the length of this string without the preceding spaces
    }

}