import java.awt.Graphics
import javax.swing.JPanel

/**
 * The class that extends JPanel that has a drawn triangle on it
 * As long as it is supplied with a solved triangle, it will have the triangle drawn on it
 */
class TriangleDrawer: JPanel(){

    /**
     * The triangle that the panel will draw
     * Will only accept solved triangles
     */
    var triangleToRepresent: Triangle? = null
        set(newTriangle){
            field = if(newTriangle == null || !newTriangle.isSolved) null else newTriangle
        }

    override fun paint(graphics: Graphics?) {
        super.paint(graphics)
        if(triangleToRepresent == null || !triangleToRepresent!!.isSolved) return
        //TODO Gets the indices of the sorted sides/angles
    }

}