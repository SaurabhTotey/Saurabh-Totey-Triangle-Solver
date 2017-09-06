/**
 * A class that defines a triangle
 * This is where all the math of the triangle is handled
 */
class Triangle(){

    /**
     * An array of all the sides
     * Each side in this array corresponds to an opposite angle in the angles array
     */
    var sides = Array(3, {_ -> -1.0})
    /**
     * An array of all the angles
     * Each angle in this array corresponds to an opposite side in the sides array
     */
    var angles = Array(3, {_ -> -1.0})
    /**
     * The type of the triangle
     * Is dynamically calculated
     */
    val type: TriangleType
        get() = TriangleType(this.sides, this.angles)

    /**
     * A class that defines the type of a triangle
     */
    class TriangleType(sides: Array<Double>, angles: Array<Double>){

        /**
         * The type of the triangle this is
         * True correlates to side
         * False correlates to angle
         */
        var type: Array<Boolean>? = null

        /**
         * Uses the given parameters to figure out the type of triangle
         */
        init{
            val hasBeenInitialized = {a: Double -> a > 0}
            val amtSides = sides.filter(hasBeenInitialized).size
            val amtAngles = angles.filter(hasBeenInitialized).size
            assert(amtSides + amtAngles >= 3)
            type = when(amtSides){
                3 ->
                    arrayOf(true, true, true)
                2 -> //Checks if the side that isn't defined has a defined opposite angle; that makes the triangle SAS
                    if(angles[arrayOf(0, 1, 2).filter{a -> sides[a] <= 0}[0]] > 0)
                        arrayOf(true, false, true)
                    else
                        arrayOf(false, true, true)
                1 ->
                    arrayOf(false, true, true)
                else -> arrayOf(false, false, false)
            }
        }

        /**
         * A string representation of this triangle type
         */
        override fun toString(): String {
            return if(type != null) type!!.joinToString{a -> if(a) "S" else "A"} else "???"
        }
    }

    /**
     * Returns a triangle with all of the sides and angles solved
     */
    fun solve(): Triangle?{
        return null
    }

}