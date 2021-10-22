package ru.smak.ui

import ru.smak.ui.painting.CartesianPainter
import ru.smak.ui.painting.FunctionPainter
import ru.smak.ui.painting.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(private val painters: List<Painter>) : JPanel() {

    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let{
            painters.forEach { p ->
                p.paint(it)
            }
        }

    }
}