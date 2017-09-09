import java.awt.Graphics
import javax.swing.JPanel

class TriangleDrawer : JPanel(){

    var triangleToRepresent: Triangle? = null

    override fun paint(graphics: Graphics?) {
        super.paint(graphics)
        if(triangleToRepresent == null) return
        //TODO handle drawing triangles with triangle information displayed
    }

}