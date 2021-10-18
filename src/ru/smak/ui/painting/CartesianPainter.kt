package ru.smak.ui.painting

import java.awt.*

class CartesianPainter(private val plane: CartesianPlane) {

    var axesColor: Color = Color.GRAY

    fun paint(g: Graphics){
        paintAxes(g)
    }

    private fun paintAxes(g: Graphics){
        with(plane) {
            (g as Graphics2D).apply {
                stroke = BasicStroke(3F)
                color = axesColor
                if (yMin <= 0 && yMax >= 0) {
                    drawLine(0, yCrt2Scr(0.0), width, yCrt2Scr(0.0))
                } else {
                    drawLine(0, 0, width, 0)
                    drawLine(0, height, width, height)
                }

                if (xMin <= 0 && xMax >= 0) {
                    drawLine(xCrt2Scr(0.0), 0, xCrt2Scr(0.0), height)
                } else {
                    drawLine(0, 0, 0, height)
                    drawLine(width, 0, width, height)
                }
            }
        }
    }

}