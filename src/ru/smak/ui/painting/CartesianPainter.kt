package ru.smak.ui.painting

import java.awt.*

class CartesianPainter(private val plane: CartesianPlane) : Painter
{

    var fontSize: Int = 16
    var intTickColor: Color = Color.RED
    var axesColor: Color = Color.GRAY

    override fun paint(g: Graphics){
        paintAxes(g)
        paintLabels(g)
    }

    private fun paintLabels(g: Graphics) {
        var oneTick = 1.0
        var tSize = 8
        with (g as Graphics2D){
            stroke = BasicStroke(1F)
            color = intTickColor
            font = Font("Cambria", Font.BOLD, fontSize)
            val (tW, tH) = fontMetrics.getStringBounds(oneTick.toString(), g).run {
                Pair(width.toFloat(), height.toFloat())
            }
            with (plane) {
                drawLine(xCrt2Scr(oneTick), yCrt2Scr(0.0)-tSize, xCrt2Scr(oneTick), yCrt2Scr(0.0)+tSize)
                drawString(oneTick.toString(), xCrt2Scr(oneTick) - tW / 2F, yCrt2Scr(0.0) + tH + tSize)
            }
        }
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