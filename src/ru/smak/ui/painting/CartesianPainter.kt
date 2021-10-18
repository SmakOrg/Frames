package ru.smak.ui.painting

import java.awt.Color
import java.awt.Graphics

class CartesianPainter(private val plane: CartesianPlane) {

    fun paint(g: Graphics){
        with(plane) {
            g.apply {
                drawLine(0, yCrt2Scr(0.0), width, yCrt2Scr(0.0))
                drawLine(xCrt2Scr(0.0), 0, xCrt2Scr(0.0), height)
            }
        }
    }
}