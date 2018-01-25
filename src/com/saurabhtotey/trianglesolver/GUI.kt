package com.saurabhtotey.trianglesolver

import kotlin.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.CanvasRenderingContext2D

val screen = document.getElementById("screen") as HTMLCanvasElement
val renderer = screen.getContext("2d") as CanvasRenderingContext2D

fun main(args: Array<String>) {
    println("Hello World!")
}