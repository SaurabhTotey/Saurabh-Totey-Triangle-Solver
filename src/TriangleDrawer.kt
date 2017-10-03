import java.awt.Color
import java.awt.Graphics
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
    val bColor = Color.GREEN
    val cColor = Color.ORANGE
    //Sets each index to a color
    val colorMap = hashMapOf(0 to aColor, 1 to bColor, 2 to cColor)

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

        //Boilerplate stuff that needs to happen; the super method of paint is called and the triangle is checked for existence and validity
        super.paint(graphics)
        if(triangleToRepresent == null || !triangleToRepresent!!.isSolved) return

        //Below bubble-sorts (not the most efficient sort, but whatever) the sides and keeps the new order of the indices
        var sides = triangleToRepresent!!.sides.clone()
        var indices = (0..2).toList().toTypedArray()
        for(i in 2 downTo 0){
            for(j in 1..i){
                if(sides[j - 1] > sides[j]){
                    sides[j] = sides[j - 1].also{sides[j - 1] = sides[j]} //Swaps the sides
                    indices[j] = indices[j - 1].also{indices[j - 1] = indices[j]} //Swaps the indices
                }
            }
        }

        //A local function that draws a line given normalized coordinates between 0 and 1 and a color
        fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double, color: Color){
            val prevColor = graphics!!.color
            graphics.color = color
            graphics.drawLine((x1 * this.width).toInt(), (y1 * this.height).toInt(), (x2 * this.width).toInt(), (y2 * this.height).toInt())
            graphics.color = prevColor
        }

        //Defines normalized coordinate bounds with which the drawer will operate; because coordinates are normalized between 0 and 1 to a square, corner val equally applies to x and y
        val smallestCornerVal = 0.1
        val largestCornerVal = 0.9

        //Draws the longest leg of the triangle as the bottom; the longest leg is at spot 2 and is always scaled to be the largest length
        drawLine(smallestCornerVal, largestCornerVal, largestCornerVal, largestCornerVal, colorMap[indices[2]]!!)
    }

}