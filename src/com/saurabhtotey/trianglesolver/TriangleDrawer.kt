package com.saurabhtotey.trianglesolver

import org.w3c.dom.CanvasRenderingContext2D
import kotlin.math.*

/**
 * A class that handles drawing triangles; takes in the rendering context with which to draw the said triangles
 */
class TriangleDrawer(val renderer: CanvasRenderingContext2D) {

    /**
     * The color pallete of the colors to be used for drawing the triangle
     */
    private val aColor = "#0000ff"
    private val bColor = "#ff0000"
    private val cColor = "#ff6600"
    //Sets each index to a color
    private val colorMap = hashMapOf(0 to aColor, 1 to bColor, 2 to cColor)
    var isRadians = true

    /**
     * The triangle to draw; must be the unsolved triangle; this draws the solutions of this triangle
     */
    var triangle: Triangle? = null
        //When a triangle is given to this to draw, it checks that it is valid and then draws it; otherwise, it clears the screen
        set(value) {
            if (value != null && value.isValid()) {
                field = value
                this.drawSolutions()
            } else {
                this.clearScreen()
                field = null
            }
        }

    /**
     * Clears the entire area that the renderer can draw on
     */
    private fun clearScreen() {
        this.renderer.clearRect(0.0, 0.0, this.renderer.canvas.width.toDouble(), this.renderer.canvas.height.toDouble())
    }

    /**
     * The actual method that handles drawing triangles; automatically gets called whenever a new triangle is given to this drawer
     * Handles getting triangle solutions and drawing 2 triangles if necessary
     */
    private fun drawSolutions() {
        this.clearScreen()
        val solutions = this.triangle!!.solutions()
        fun drawTriangle(triangleToRepresent: Triangle, x: Int, y: Int, width: Int, height: Int) {
            //Below bubble-sorts (not the most efficient sort, but whatever) the sides and angles and keeps the new order of the indices
            val sides = triangleToRepresent.sides.sliceArray(0..3)
            val angles = triangleToRepresent.angles.sliceArray(0..3)
            val indices = (0..2).toList().toTypedArray()
            for (i in 2 downTo 0) {
                for (j in 1..i) {
                    if (sides[j - 1] > sides[j]) {
                        sides[j] = sides[j - 1].also { sides[j - 1] = sides[j] } //Swaps the sides
                        angles[j] = angles[j - 1].also { angles[j - 1] = angles[j] } //Swaps the angles
                        indices[j] = indices[j - 1].also { indices[j - 1] = indices[j] } //Swaps the indices
                    }
                }
            }
            //Define some constants that are useful for scaling and centering the triangle drawing
            val triangleWidth = if (height < width) height else width - 10 //Is the triangle width because the largest side is always at the bottom and is scaled to this value
            val triangleHeight = (sides[1] * sin(angles[0]) * triangleWidth / sides[2]).toInt()
            val leftX = x + (width - triangleWidth) / 2
            val rightX = leftX + triangleWidth
            val bottomY = y + (height - triangleHeight) / 2 + triangleHeight
            val meetingX = leftX + (cos(angles[1]) * sides[0] * triangleWidth / sides[2]).toInt()
            val meetingY = bottomY - triangleHeight
            //Actually draws the triangle
            fun drawLine(x0: Int, y0: Int, x1: Int, y1: Int) {
                this.renderer.beginPath()
                this.renderer.moveTo(x0.toDouble(), y0.toDouble())
                this.renderer.lineTo(x1.toDouble(), y1.toDouble())
                this.renderer.stroke()
                this.renderer.closePath()
            }
            this.renderer.strokeStyle = colorMap[indices[2]]
            drawLine(leftX, bottomY, rightX, bottomY) //Largest side is always horizontally oriented at the bottom
            this.renderer.strokeStyle = colorMap[indices[0]]
            drawLine(leftX, bottomY, meetingX, meetingY) //Smallest side is always the left leg of the triangle
            this.renderer.strokeStyle = colorMap[indices[1]]
            drawLine(rightX, bottomY, meetingX, meetingY) //Medium side is always the right leg of the triangle
            //Draws all the dots of the triangle and corresponds the colors of the dots with the opposite side's color
            this.renderer.strokeStyle = colorMap[indices[2]]
            drawLine(meetingX, meetingY, meetingX, meetingY) //Draws the dot at the top with the same color as the largest side
            this.renderer.strokeStyle = colorMap[indices[0]]
            drawLine(rightX, bottomY, rightX, bottomY) //Draws the dot at the right with the same color as the left leg
            this.renderer.strokeStyle = colorMap[indices[1]]
            drawLine(leftX, bottomY, leftX, bottomY) //Draws the dot at the left with the same color as the right leg
            //Below draws text that displays triangle information
            this.renderer.font = "${triangleWidth / 35}px Courier New"
            val stringParts = arrayOf("a", "b", "c", "A", "B", "C")
            val truncateLength = 5
            val lineSpacing = 2 * triangleWidth / 35
            fun formatString(toFormat: Double): String {
                val numLength = toFormat.toString().length
                val number: String = toFormat.toString().substring(0..if (numLength - 1 < truncateLength + 1) numLength - 1 else truncateLength + 1)
                return when {
                    number.endsWith("0") && !number.endsWith(".0") -> formatString(number.substring(0 until number.length).toDouble())
                    number.length >= truncateLength + 1 -> {
                        when {
                            number.length >= truncateLength + 2 && number[truncateLength + 1] != '.' && (number[truncateLength + 1].toString().toInt() >= 5) -> {
                                //Is turned to string first because toInt for a char returns char code
                                formatString((number.substring(0 until truncateLength) + (number[truncateLength].toInt() + 1)).toDouble())
                            }
                            toFormat > "9".repeat(truncateLength - 1).toDouble() -> {
                                val power = number.indexOf(".") - 1
                                val truncIndex = truncateLength - power.toString().length - 1
                                number[0] + "." + number.replace(".", "").substring(1, truncIndex - 1) + (number[truncIndex - 1] + if (number[truncIndex].toString().toInt() >= 5) 1 else 0) + "E" + power
                            }
                            else -> number.substring(0..truncateLength)
                        }
                    }
                    else -> number + " ".repeat(truncateLength + 1 - number.length)
                }
            }
            for (i in 0..2) {
                this.renderer.strokeStyle = colorMap[i]
                this.renderer.strokeText(stringParts[i] + " = " + formatString(triangleToRepresent.sides[i]) + " " + stringParts[i + 3] + " = " + if (isRadians) {
                    formatString(triangleToRepresent.angles[i])
                } else {
                    formatString(asDegrees(triangleToRepresent.angles[i]))
                }, x + lineSpacing / 2.0, y + (i + 1.0) * lineSpacing)
            }
            this.renderer.strokeStyle = "#000000"
            this.renderer.strokeText(" ".repeat((19 - 12) / 2) + "Area = " + formatString(triangleToRepresent.area()), x + lineSpacing / 2.0, y + 4.0 * lineSpacing) //19 is the length of the above strings, 12 is the length of this string without the preceding spaces
        }
        if (solutions.size == 2) {
            drawTriangle(solutions[0], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop, this.renderer.canvas.width, this.renderer.canvas.height / 2)
            drawTriangle(solutions[1], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop + this.renderer.canvas.height / 2, this.renderer.canvas.width, this.renderer.canvas.height / 2)
        } else {
            drawTriangle(solutions[0], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop, this.renderer.canvas.width, this.renderer.canvas.height)
        }
    }

}