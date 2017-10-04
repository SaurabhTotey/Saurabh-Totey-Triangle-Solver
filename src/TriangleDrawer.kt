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
    val bColor = Color.PINK
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
            //Because x and y scale the same, if either the x space or the y space is bigger, the offset will combat that so any drawing is scaled correctly and centered
            var xOffset = 0
            var yOffset = 0
            if(this.height < this.width){
                xOffset = (this.width - this.height) / 2
            }else{
                yOffset = (this.height - this.width) / 2
            }
            //Stores the previous color, changes the drawer's color to the desired color, draws the line with the new color, and then restores the old color to the drawer
            val prevColor = graphics!!.color
            graphics.color = color
            graphics.drawLine(xOffset + (x1 * this.height).toInt(), yOffset + (y1 * this.height).toInt(), xOffset + (x2 * this.height).toInt(), yOffset + (y2 * this.height).toInt())
            graphics.color = prevColor
        }

        //Defines normalized coordinate bounds with which the drawer will operate; because coordinates are normalized between 0 and 1 to a square, corner val equally applies to x and y
        val smallestCornerVal = 0.1
        val largestCornerVal = 0.9

        //Draws the longest leg of the triangle as the bottom; the longest leg is at spot 2 and is always scaled to be the largest length
        drawLine(smallestCornerVal, largestCornerVal, largestCornerVal, largestCornerVal, colorMap[indices[2]]!!)
        //Draws the smallest leg of the triangle as the leg to the left of the bottom leg and scales it appropriately
        val scaledSmallestSideLength = (largestCornerVal - smallestCornerVal) * sides[indices[0]] / sides[indices[2]]
        drawLine(smallestCornerVal, largestCornerVal, smallestCornerVal + scaledSmallestSideLength * Math.cos(triangleToRepresent!!.angles[indices[1]]), largestCornerVal - scaledSmallestSideLength * Math.sin(triangleToRepresent!!.angles[indices[1]]), colorMap[indices[0]]!!)
        //Draws the medium sized leg of the triangle as the leg to the right of the bottom leg and scales it appropriately
        val scaledMediumSideLength = (largestCornerVal - smallestCornerVal) * sides[indices[1]] / sides[indices[2]]
        drawLine(largestCornerVal, largestCornerVal, largestCornerVal - scaledMediumSideLength * Math.cos(triangleToRepresent!!.angles[indices[0]]), largestCornerVal - scaledMediumSideLength * Math.sin(triangleToRepresent!!.angles[indices[0]]), colorMap[indices[1]]!!)

        //TODO display actual triangle information and numbers here

    }

}