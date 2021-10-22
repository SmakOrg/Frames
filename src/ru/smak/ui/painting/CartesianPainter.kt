package ru.smak.ui.painting

import java.awt.*

class CartesianPainter(private val plane: CartesianPlane) : Painter{

    var mainFont: Font = Font("Cambria", Font.BOLD, 16)
    var maxTickColor: Color = Color.RED

    override fun paint(g: Graphics) {
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
                drawXLabels(g)
            }
        }
    }

    private fun drawXLabels(g: Graphics) {
        with (g as Graphics2D){
            stroke = BasicStroke(2F)
            color = maxTickColor
            font = mainFont
            var tickValue = 1.0
            with(plane) {
                var tSize = 8
                drawLine(xCrt2Scr(tickValue), yCrt2Scr(0.0)-tSize, xCrt2Scr(tickValue), yCrt2Scr(0.0)+tSize)
                val (tW, tH) = with(fontMetrics.getStringBounds(tickValue.toString(), g)){
                    Pair(width.toInt(), height.toInt())
                }
                drawString(tickValue.toString(), xCrt2Scr(tickValue) - tW/2, yCrt2Scr(0.0) + tSize + tH)
            }
        }
    }
}