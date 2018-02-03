package com.saurabhtotey.trianglesolver

import org.w3c.dom.*
import kotlin.browser.document
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
    //Gives the componentSelect dropdowns all of the options and sets the dropdowns and typeIO box to always correspond
    val stringParts = arrayOf("a", "b", "c", "A", "B", "C")
    val typeIO = document.getElementById("triangleType") as HTMLInputElement
    val componentDropdowns = document.getElementsByClassName("componentSelect").asList().map { it as HTMLSelectElement }
    componentDropdowns.forEachIndexed { index, element ->
        //Gives each box all of the triangle component options
        for (letter in stringParts) {
            val option = document.createElement("OPTION") as HTMLOptionElement
            option.value = letter
            option.text = letter
            element.add(option)
        }
        //Gives the boxes a unique starting index (0, 1, 2) to start as ("a", "b", "c")
        element.selectedIndex = index
        //Adds an input listener to the boxes that changes the typeIO box to match the type of the selected options
        element.oninput = {
            val partsArray = Array(6, { _ -> -1.0 }) //Indexes 0..2 are sides, indexes 3..5 are angles
            for (dropdown in componentDropdowns) {
                partsArray[dropdown.selectedIndex] = 1.0
            }
            typeIO.value = TriangleType(partsArray.sliceArray(0..2), partsArray.sliceArray(3..5)).toString()
            null
        }
    }
    typeIO.value = "SSS"
    typeIO.oninput = {
        //Formats any inputs to this box correctly
        if (typeIO.value.length > 3) {
            typeIO.value = typeIO.value.substring(0..2)
        }
        typeIO.value = typeIO.value.toUpperCase()
        for (letter in typeIO.value) {
            if (!"AS".contains(letter)) {
                typeIO.value = typeIO.value.replace(letter.toString(), "")
            }
        }
        //Changes the componentSelect dropdown boxes to match the typeIO box
        if (typeIO.value.length == 3) {
            if (typeIO.value[1] != typeIO.value[0] && typeIO.value[0] == typeIO.value[2]) { //If the middle char is unique
                for (i in 0..2) {
                    componentDropdowns[i].selectedIndex = i + if (typeIO.value[i] == 'A') 3 else 0
                }
            } else {
                var sideIndex = 0
                var angleIndex = 3
                for (i in 0..2) {
                    componentDropdowns[i].selectedIndex = if (typeIO.value[i] == 'S') sideIndex++ else angleIndex++
                }
            }
        }
        null
    }
}