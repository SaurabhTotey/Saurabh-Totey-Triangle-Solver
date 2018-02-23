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
            value!!
            this.renderer.restore()
            field = value
            this.drawSolutions()
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
        //TODO: draw triangles and do what this method says it will do
    }

}