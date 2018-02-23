package com.saurabhtotey.trianglesolver

import org.w3c.dom.CanvasRenderingContext2D

/**
 * A class that handles drawing triangles; takes in the rendering context with which to draw the said triangles
 */
class TriangleDrawer(val renderer: CanvasRenderingContext2D) {

    /**
     * The triangle to draw; must be the unsolved triangle; this draws the solutions of this triangle
     */
    var triangle: Triangle? = null
        //When a triangle is given to this to draw, it restores a blank screen and then draws the triangle
        set(value) {
            this.renderer.restore()
            field = value
            try {
                this.drawSolutions()
            } catch (e: Exception) {
                this.renderer.restore()
                field = null
            }
        }

    /**
     * When the drawer is created, it makes the renderer save the blank screen so that it can be restored again later
     */
    init {
        this.renderer.save() //Saves blank canvas so that whenever the triangle is changed, it can be restored
    }

    /**
     * The actual method that handles drawing triangles; automatically gets called whenever a new triangle is given to this drawer
     * Handles getting triangle solutions and drawing 2 triangles if necessary
     */
    private fun drawSolutions() {
        val solutions = this.triangle!!.solutions()
        fun drawTriangle(toDraw: Triangle, x: Int, y: Int, w: Int, h: Int) {
            //TODO: use renderer to draw given rectangle within given rectangle
        }
        if (solutions.size == 2) {
            drawTriangle(solutions[0], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop, this.renderer.canvas.width, this.renderer.canvas.height / 2)
            drawTriangle(solutions[1], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop + this.renderer.canvas.height / 2, this.renderer.canvas.width, this.renderer.canvas.height / 2)
        } else {
            drawTriangle(solutions[0], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop, this.renderer.canvas.width, this.renderer.canvas.height)
        }
    }

}