package com.saurabhtotey.trianglesolver

import kotlin.js.Math.pow //This import is used instead of import kotlin.math.pow because that doesn't work
import kotlin.math.*

val hasBeenInitialized = { a: Double -> a > 0 } //A function that will return whether a triangle angle or side has been initialized
/**
 * A function that will return indices given a certain predicate
 */
fun getIndicesSuchThat(predicate: (Int) -> Boolean): List<Int> {
    return arrayOf(0, 1, 2).filter { predicate(it) }
}

/**
 * A function that will convert radian angles to degrees
 */
fun asDegrees(radians: Double): Double {
    return radians * 180 / PI
}

/**
 * A function that will convert degrees angles to radians
 */
fun asRadians(degrees: Double): Double {
    return degrees * PI / 180
}

/**
 * A class that defines a triangle
 * This is where all the math of the triangle is handled
 * This logic class uses radians as do all people of logic
 */
class Triangle(var sides: Array<Double>, var angles: Array<Double>) {

    constructor() : this(Array(3, { _ -> -1.0 }), Array(3, { _ -> -1.0 }))

    /**
     * This just figures out if the triangle is valid or not with the given inputs
     * If the triangle isn't solved, it calls this method on the solutions of the triangles
     */
    fun isValid(): Boolean {
        if (this.isSolved) {
            val acceptableError = 0.005
            val anglesAddToPi = this.angles.sum() < PI + acceptableError && this.angles.sum() > PI - acceptableError
            val sidesFulfillLegInequality = this.sides[0] < this.sides[1] + this.sides[2] && this.sides[1] < this.sides[0] + this.sides[2] && this.sides[2] < this.sides[0] + this.sides[1]
            return anglesAddToPi && sidesFulfillLegInequality
        } else {
            return try {
                //If any of the solutions aren't valid, the unsolved triangle isn't valid
                this.solutions().filter { !it.isSolved || !it.isValid() } .forEach { return false }
                true
            } catch (e: Exception) {
                //The triangle can't be made into a solution and is thus invalid
                false
            }
        }
    }

    /**
     * The type of the triangle
     * Is dynamically calculated
     */
    private val type: TriangleType
        get() = TriangleType(this.sides, this.angles)
    /**
     * Whether the triangle has been solved completely
     * Is dynamically calculated
     */
    val isSolved: Boolean
        get() = this.sides.filter { hasBeenInitialized(it) }.size == 3 && this.angles.filter { hasBeenInitialized(it) }.size == 3

    /**
     * Returns a triangle with all of the sides and angles solved
     * Doesn't actually modify base triangle
     * Returns an array because solving for an ASS triangle with the law of sines may return two triangles
     */
    fun solutions(): Array<Triangle> {
        var solved = arrayOf(this.copy())
        var primary = solved[0]
        fun reSolve() {
            solved = primary.solutions()
        }
        when (this.type) {
            SSS -> {
                primary.angles[0] = acos((pow(primary.sides[1], 2.0) + pow(primary.sides[2], 2.0) - pow(primary.sides[0], 2.0)) / (2 * primary.sides[1] * primary.sides[2])) //Using the law of cosines
                primary.angles[1] = acos((pow(primary.sides[0], 2.0) + pow(primary.sides[2], 2.0) - pow(primary.sides[1], 2.0)) / (2 * primary.sides[0] * primary.sides[2])) //Using the law of cosines
                primary.angles[2] = PI - primary.angles[0] - primary.angles[1] //Because all angles must add up to π
            }
            SAS -> {
                val unknownSideIndex = getIndicesSuchThat { !hasBeenInitialized(primary.sides[it]) }[0]
                val unknownAngleIndices = getIndicesSuchThat { it != unknownSideIndex }
                primary.sides[unknownSideIndex] = sqrt(pow(solved[0].sides[unknownAngleIndices[0]], 2.0) + pow(primary.sides[unknownAngleIndices[1]], 2.0) - 2 * primary.sides[unknownAngleIndices[0]] * primary.sides[unknownAngleIndices[1]] * cos(primary.angles[unknownSideIndex])) //Using the law of cosines
                reSolve() //Will solve the triangle as if it were SSS
            }
            AAA -> {
                val smallestSide = getIndicesSuchThat { primary.angles[it] == primary.angles.min() }[0]
                primary.sides[smallestSide] = 1.0 //Because no sides are defined, the smallest side is then assumed to be 1
                reSolve() //Will solve the triangle as if it were ASA
            }
            ASA -> {
                val knownAngleIndices = getIndicesSuchThat { hasBeenInitialized(primary.angles[it]) }
                val knownSideIndices = getIndicesSuchThat { hasBeenInitialized(primary.sides[it]) }
                if (knownAngleIndices.size == 2) { //Will solve the remaining angle if it is yet unsolved
                    primary.angles[getIndicesSuchThat { it !in knownAngleIndices }[0]] = PI - primary.angles[knownAngleIndices[0]] - primary.angles[knownAngleIndices[1]] //Because all angles must add up to π
                }
                for (unknownIndex in getIndicesSuchThat { it !in knownSideIndices }) { //Will solve for any unsolved sides now that all angles are solved
                    primary.sides[unknownIndex] = sin(primary.angles[unknownIndex]) * primary.sides[knownSideIndices[0]] / sin(primary.angles[knownSideIndices[0]]) //Using the law of sines
                }
            }
            AAS -> {
                val unknownAngleIndex = getIndicesSuchThat { !hasBeenInitialized(primary.angles[it]) }[0]
                val knownAngleIndices = getIndicesSuchThat { it != unknownAngleIndex }
                primary.angles[unknownAngleIndex] = PI - primary.angles[knownAngleIndices[0]] - primary.angles[knownAngleIndices[1]] //Because all angles must add up to π
                reSolve() //Will solve the triangle as if it were ASA
            }
            ASS -> {
                val knownSides = getIndicesSuchThat { hasBeenInitialized(primary.sides[it]) }
                val knownAngle = getIndicesSuchThat { hasBeenInitialized(primary.angles[it]) }[0]
                val unknownOppositeAngle = knownSides.filter { it != knownAngle }[0]
                val ASSType = primary.sides[unknownOppositeAngle] / primary.sides[knownAngle] * sin(primary.angles[knownAngle])
                if (primary.sides[knownAngle] >= primary.sides[unknownOppositeAngle]) {
                    primary.angles[unknownOppositeAngle] = asin(ASSType)
                    reSolve()
                } else {
                    primary = solved[0]
                    val secondary = this.copy()
                    primary.angles[unknownOppositeAngle] = asin(ASSType)
                    secondary.angles[unknownOppositeAngle] = PI - asin(ASSType)
                    solved = arrayOf(primary.solutions()[0], secondary.solutions()[0])
                }
            }
        }
        return solved
    }

    /**
     * Gets the area of the triangle using Heron's method
     * Only works if this is a solved triangle
     * Doesn't return the area of this triangle's solved triangle because there is the possibility (from ASS triangles) that 2 areas might be returned
     */
    fun area(): Double {
        if (!this.isSolved) return 0.0
        val s = this.sides.sum() / 2
        return sqrt(s * (s - this.sides[0]) * (s - this.sides[1]) * (s - this.sides[2]))
    }

    /**
     * Creates a copy of this triangle with the same initial properties
     */
    private fun copy(): Triangle {
        val clone = Triangle()
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
val ASS = TriangleType("ASS") //Does not necessarily define a unique triangle

/**
 * A class that defines the type of a triangle
 */
class TriangleType {

    /**
     * An enum for the parts of a triangle
     */
    enum class Part {
        SIDE, ANGLE, UNKNOWN
    }

    /**
     * The type of the triangle this is
     * Contains 3 Parts
     */
    var type = Array(3, { _ -> Part.UNKNOWN })

    /**
     * Uses the given parameters to figure out the type of triangle
     */
    constructor(sides: Array<Double>, angles: Array<Double>) {
        val initializedSides = getIndicesSuchThat { hasBeenInitialized(sides[it]) }
        val initializedAngles = getIndicesSuchThat { hasBeenInitialized(angles[it]) }
        if (initializedSides.size + initializedAngles.size < 3) return
        //Sets the type to the first applicable found triangle type; order is checked in terms of desirability (eg. least desirable types checked last)
        //That way if a triangle fulfills the condition of a desirable type and an undesirable type, it will get checked against the desirable type first and thus become it
        this.type = when (initializedSides.size) {
            3 -> arrayOf(Part.SIDE, Part.SIDE, Part.SIDE)
            1 -> //1 is checked before 2 because 2 has the possibility of making an ASS triangle when a AAS triangle is possible, and ASS is the least desirable triangle
                //If there are 3 angles and 1 side or if the side opposite the uninitialized angle is initialized, the triangle is ASA, otherwise it is AAS
                if (initializedAngles.size == 3 || hasBeenInitialized(sides[getIndicesSuchThat { it !in initializedAngles }[0]]))
                    arrayOf(Part.ANGLE, Part.SIDE, Part.ANGLE)
                else
                    arrayOf(Part.ANGLE, Part.ANGLE, Part.SIDE)

            2 ->
                when {
                //If the angle opposite the uninitialized side is initialized, the triangle is SAS, or if all 3 angles are initialized
                    hasBeenInitialized(angles[getIndicesSuchThat { it !in initializedSides }[0]]) || initializedAngles.size == 3 -> arrayOf(Part.SIDE, Part.ANGLE, Part.SIDE)
                //If there are two angles and two sides, but there is no included angle
                    initializedAngles.size > 1 -> arrayOf(Part.ANGLE, Part.ANGLE, Part.SIDE)
                    else -> arrayOf(Part.ANGLE, Part.SIDE, Part.SIDE)
                }

            0 -> arrayOf(Part.ANGLE, Part.ANGLE, Part.ANGLE)
            else -> this.type
        }
    }

    /**
     * Just makes a type based on the given string
     */
    constructor(stringType: String) {
        this.type = stringType.map {
            when (it.toLowerCase()) {
                's' -> Part.SIDE
                'a' -> Part.ANGLE
                else -> Part.UNKNOWN
            }
        }.toTypedArray()
    }

    /**
     * A string representation of this triangle type
     */
    override fun toString(): String {
        return type.joinToString("") {
            when (it) {
                Part.SIDE -> "S"
                Part.ANGLE -> "A"
                Part.UNKNOWN -> "?"
            }
        }
    }

    /**
     * Returns whether two triangle types are equal
     * Accounts for palindromes
     */
    override fun equals(other: Any?): Boolean {
        return other is TriangleType && (other.type.contentEquals(this.type) || other.type.contentEquals(this.type.reversedArray()))
    }

}