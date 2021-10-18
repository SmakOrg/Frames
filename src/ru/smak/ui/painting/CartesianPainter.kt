package ru.smak.ui.painting

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class CartesianPainter(private val plane: CartesianPlane) {

    fun paint(g: Graphics) {
        with(plane) {
            (g as Graphics2D).apply {
                stroke = BasicStroke(3F)
                if ((xMin > 0) or (xMax < 0)) {
                    drawLine(width, 0, width, height)
                    drawLine(0, 0, 0, height)
                } else drawLine(xCrt2Scr(0.0), 0, xCrt2Scr(0.0), height)
                if ((yMin > 0) or (yMax < 0)) {
                    drawLine(0, height, width, height)
                    drawLine(0, 0, width, 0)
                } else drawLine(0, yCrt2Scr(0.0), width, yCrt2Scr(0.0))
            }
        }
    }
}