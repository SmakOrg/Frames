package ru.smak.ui.painting

import java.awt.Color
import java.awt.Graphics

class CartesianPainter(private val plane: CartesianPlane) {

    fun paint(g: Graphics){
        g.apply {
            drawLine(0, plane.yCrt2Scr(0.0), plane.width, plane.yCrt2Scr(0.0))
            drawLine(plane.xCrt2Scr(0.0), 0, plane.xCrt2Scr(0.0), plane.height)
        }
    }

}