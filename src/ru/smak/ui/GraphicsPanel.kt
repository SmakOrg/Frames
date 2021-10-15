package ru.smak.ui

import ru.smak.ui.painting.CartesianPainter
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(val painter: CartesianPainter) : JPanel() {

    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let{painter.paint(it)}
    }
}