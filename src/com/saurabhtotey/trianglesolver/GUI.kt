package com.saurabhtotey.trianglesolver

import kotlin.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLDivElement

val screen = document.getElementById("screen") as HTMLCanvasElement
val ioPane = document.getElementById("ioPane") as HTMLDivElement
val anglesPane = document.getElementById("anglesPane") as HTMLDivElement
val renderer = screen.getContext("2d") as CanvasRenderingContext2D

fun main(args: Array<String>) {
    println("Hello World!")
}