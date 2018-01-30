package com.saurabhtotey.trianglesolver

import kotlin.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLInputElement
import kotlin.browser.window
import kotlin.math.roundToInt

/**
 * A function that evaluates a string as a number (eg. 5 -> 5, 2pi -> 6.28, etc.)
 */
@JsName("evaluateMath")
external fun evaluate(expression: String): Double

/**
 * Entry point of the program
 */
fun main(args: Array<String>) {
    ///The actual rendering area to draw the triangles; is an HTML canvas
    val screen = document.getElementById("screen") as HTMLCanvasElement
    ///The utility object which draws to the screen
    val renderer = screen.getContext("2d") as CanvasRenderingContext2D
    /**
     * Defines a method to fit the rendering area to the available screen space
     */
    fun fitScreen() {
        screen.width = (screen.parentElement!!.clientWidth * 0.75).roundToInt()
        screen.height = (screen.width * 0.5).roundToInt()
    }
    //Sets the window to always resize/fit screen when window size changes
    window.onresize = { fitScreen(); null }
    //Fits the screen when the program enters
    fitScreen()
    //Finds the degrees and radians conversion boxes and sets them to update the other box with the equivalent number
    val degreesBox = document.getElementById("degreesBox") as HTMLInputElement
    val radiansBox = document.getElementById("radiansBox") as HTMLInputElement
    degreesBox.oninput = {
        radiansBox.value = (evaluate("pi * ${degreesBox.value} / 180")).toString()
        null
    }
    radiansBox.oninput = {
        degreesBox.value = (evaluate("180 * ${radiansBox.value} / pi")).toString()
        null
    }
}