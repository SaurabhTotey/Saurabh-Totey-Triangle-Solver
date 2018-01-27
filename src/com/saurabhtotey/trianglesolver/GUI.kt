package com.saurabhtotey.trianglesolver

import kotlin.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLDivElement
import kotlin.browser.window
import kotlin.math.roundToInt

@JsName("evaluateMath")
external fun evaluate(expression: String): Double

val screen = document.getElementById("screen") as HTMLCanvasElement
val ioPane = document.getElementById("ioPane") as HTMLDivElement
val anglesPane = document.getElementById("anglesPane") as HTMLDivElement
val renderer = screen.getContext("2d") as CanvasRenderingContext2D

fun main(args: Array<String>) {

    fun fitScreen() {
        screen.width = (screen.parentElement!!.clientWidth * 0.75).roundToInt()
        screen.height = (window.innerHeight * 0.5).roundToInt()
    }

    window.onresize = { fitScreen(); null }
    fitScreen()
}