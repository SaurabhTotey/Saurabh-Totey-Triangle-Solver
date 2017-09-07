import java.lang.Math.*
import java.util.*

val hasBeenInitialized = {a: Double -> a > 0}

/**
 * A class that defines a triangle
 * This is where all the math of the triangle is handled
 * This logic class uses radians as do all people of logic
 */
class Triangle{

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
     * Whether the triangle has been solved completely
     * Is dynamically calculated
     */
    val isSolved: Boolean
        get() = this.sides.filter{a -> hasBeenInitialized(a)}.size == 3 && this.angles.filter{a -> hasBeenInitialized(a)}.size == 3

    /**
     * Returns a triangle with all of the sides and angles solved
     * Doesn't actually modify base triangle
     * Returns an array because solving for an ASS triangle with the law of sines may return two triangles
     */
    fun solution(): Array<Triangle>{
        var solved = arrayOf(this.copy())
        when(this.type){
            SSS -> {
                solved[0].angles[0] = acos((pow(solved[0].sides[1], 2.0) + pow(solved[0].sides[2], 2.0) - pow(solved[0].sides[0], 2.0)) / (2 * solved[0].sides[1] * solved[0].sides[2])) //Using the law of cosines
                solved[0].angles[1] = acos((pow(solved[0].sides[0], 2.0) + pow(solved[0].sides[2], 2.0) - pow(solved[0].sides[1], 2.0)) / (2 * solved[0].sides[0] * solved[0].sides[2])) //Using the law of cosines
                solved[0].angles[2] = Math.PI - solved[0].angles[0] - solved[0].angles[1]  //Because all angles must add up to π
            }
            SAS -> {
                val unknownSideIndex = arrayOf(0, 1, 2).filter{a -> this.sides[a] <= 0}[0]
                val unknownAngleIndices = arrayOf(0, 1, 2).filter{a -> a != unknownSideIndex}
                solved[0].sides[unknownSideIndex] = sqrt(pow(solved[0].sides[unknownAngleIndices[0]], 2.0) + pow(solved[0].sides[unknownAngleIndices[1]], 2.0) - 2 * solved[0].sides[unknownAngleIndices[0]] * solved[0].sides[unknownAngleIndices[1]] * cos(solved[0].angles[unknownSideIndex])) //Using the law of cosines
                solved[0].angles[unknownAngleIndices[0]] = acos((pow(solved[0].sides[unknownAngleIndices[1]], 2.0) + pow(solved[0].sides[unknownSideIndex], 2.0) - pow(solved[0].sides[unknownAngleIndices[0]], 2.0) ) / (2 * solved[0].sides[unknownAngleIndices[1]] * solved[0].sides[unknownSideIndex])) //Using the law of cosines
                solved[0].angles[unknownAngleIndices[1]] = Math.PI - solved[0].angles[unknownSideIndex] - solved[0].angles[unknownAngleIndices[0]]
            }
            AAA -> {

            }
            ASA -> {

            }
            AAS -> {

            }
            ASS -> {
                solved = arrayOf(this.copy(), this.copy())
            }
        }
        return solved
    }

    /**
     * Creates a copy of this triangle with the same initial properties
     */
    fun copy(): Triangle{
        var clone = Triangle()
        clone.sides = this.sides.copyOf()
        clone.angles = this.angles.copyOf()
        return clone
    }

}

//Defines all triangle types below
val SSS = TriangleType("SSS")
val SAS = TriangleType("SAS")
val AAA = TriangleType("AAA") //Does not define a unique triangle
val ASA = TriangleType("ASA")
val AAS = TriangleType("AAS")
val ASS = TriangleType("ASS") //Does not define a unique triangle

/**
 * A class that defines the type of a triangle
 */
class TriangleType{

    /**
     * An enum for the parts of a triangle
     */
    enum class Part{
        SIDE, ANGLE
    }

    /**
     * The type of the triangle this is
     * Contains 3 Parts
     */
    var type: Array<Part>? = null

    /**
     * Uses the given parameters to figure out the type of triangle
     */
    constructor(sides: Array<Double>, angles: Array<Double>){
        val amtSides = sides.filter(hasBeenInitialized).size
        val amtAngles = angles.filter(hasBeenInitialized).size
        assert(amtSides + amtAngles >= 3)
        this.type = when(amtSides){
            3 ->
                arrayOf(Part.SIDE, Part.SIDE, Part.SIDE)
            2 -> //Checks if the side that isn't defined has a defined opposite angle; that makes the triangle SAS
                if(angles[arrayOf(0, 1, 2).filter{a -> sides[a] <= 0}[0]] > 0)
                    arrayOf(Part.SIDE, Part.ANGLE, Part.SIDE)
                else
                    arrayOf(Part.ANGLE, Part.SIDE, Part.SIDE)
            1 -> //Checks if the
                if(sides[arrayOf(0, 1, 2).filter{a -> angles[a] <= 0}[0]] > 0)
                    arrayOf(Part.ANGLE, Part.SIDE, Part.ANGLE)
                else
                    arrayOf(Part.ANGLE, Part.ANGLE, Part.SIDE)
            else -> arrayOf(Part.ANGLE, Part.ANGLE, Part.ANGLE)
        }
    }

    /**
     * Just makes a type based on the given string
     */
    constructor(stringType: String){
        this.type = stringType.map{a -> if(a.toLowerCase() == 's') Part.SIDE else Part.ANGLE}.toTypedArray()
        assert(this.type!!.size == 3)
    }

    /**
     * A string representation of this triangle type
     */
    override fun toString(): String {
        return if(type != null) type!!.joinToString{a -> if(a == Part.SIDE) "S" else "A"} else "???"
    }

    /**
     * Returns whether two triangle types are equal
     * Accounts for palindromes
     */
    override fun equals(other: Any?): Boolean {
        return other is TriangleType && (Arrays.equals(other.type, this.type) || Arrays.equals(other.type, this.type!!.reversedArray()))
    }

}