package ru.smak.ui.painting

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class FunctionPainter(private val plane: CartesianPlane) : Painter{

    var funColor: Color = Color.BLUE

    override fun paint(g: Graphics){
        with(g as Graphics2D) {
            color = funColor
            stroke = BasicStroke(4F)
            with(plane) {
                for (i in 0 until width) {
                    drawLine(
                        i,
                        yCrt2Scr(Math.sin(xScr2Crt(i))),
                        i + 1,
                        yCrt2Scr(Math.sin(xScr2Crt(i + 1))),
                    )
                }
            }
        }
    }

}