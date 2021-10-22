package ru.smak.ui

import ru.smak.Test
import ru.smak.ui.painting.CartesianPainter
import ru.smak.ui.painting.CartesianPlane
import ru.smak.ui.painting.FunctionPainter
import java.awt.Color
import java.awt.Dimension
import java.awt.event.*
import javax.swing.*
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

class MainFrame : JFrame(){

    val mainPanel: GraphicsPanel
    val controlPanel: JPanel

    val xMin: JSpinner
    val xMinM: SpinnerNumberModel
    val xMax: JSpinner
    val xMaxM: SpinnerNumberModel
    val yMin: JSpinner
    val yMinM: SpinnerNumberModel
    val yMax: JSpinner
    val yMaxM: SpinnerNumberModel

    init {

        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(600, 400)

        xMinM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        xMin = JSpinner(xMinM)
        xMaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        xMax = JSpinner(xMaxM)
        yMinM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        yMin = JSpinner(yMinM)
        yMaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        yMax = JSpinner(yMaxM)

        val plane = CartesianPlane(
            xMin.value as Double,
            xMax.value as Double,
            yMin.value as Double,
            yMax.value as Double,
        )
        val cartesianPainter = CartesianPainter(plane)
        val sinPainter = FunctionPainter(plane, Math::sin)
        val cosPainter = FunctionPainter(plane, Math::cos)
        cosPainter.funColor = Color.GREEN
        val test1 = Test(3)
        val test2 = Test(1)
        val test1Painter = FunctionPainter(plane, test1::f)
        test1Painter.funColor = Color.ORANGE
        val test2Painter = FunctionPainter(plane, test2::f)
        test2Painter.funColor = Color.PINK
        val painters = mutableListOf(cartesianPainter, sinPainter, cosPainter, test1Painter, test2Painter)
        mainPanel = GraphicsPanel(painters).apply {
            background = Color.WHITE
        }
        mainPanel.addMouseListener(object: MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                super.mouseClicked(e)
                if (painters.size>0) painters.removeAt(0)
                mainPanel.repaint()
            }
        })
        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                plane.width = mainPanel.width
                plane.height = mainPanel.height
                mainPanel.repaint()
            }
        })

        controlPanel = JPanel().apply{
            background = Color.RED
        }
        layout = GroupLayout(contentPane).apply{
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                            .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    )
                    .addGap(4)
            )

            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(4)
                    .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(4)
            )
        }

        /*xMax.addChangeListener(object : ChangeListener{
            override fun stateChanged(e: ChangeEvent?) {
                TODO("Not yet implemented")
            }
        })*/
        xMin.addChangeListener{
            xMaxM.minimum = xMin.value as Double + 0.1
            plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        xMax.addChangeListener{
            xMinM.maximum = xMax.value as Double - 0.1
            plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        yMin.addChangeListener{
            yMaxM.minimum = yMin.value as Double + 0.1
            plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }
        yMax.addChangeListener{
            yMinM.maximum = yMax.value as Double - 0.1
            plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }

        controlPanel.layout = GroupLayout(controlPanel).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(xMin, 100, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yMin, 100, 100, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(8)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(xMax, 100, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yMax, 100, 100, GroupLayout.PREFERRED_SIZE)
                    )

                    .addGap(4, 4, Int.MAX_VALUE)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(xMin, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(xMax, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(8)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(yMin, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yMax, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )

                    .addGap(4)
            )
        }

        pack()

        plane.width = mainPanel.width
        plane.height = mainPanel.height
    }
}