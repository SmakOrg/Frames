package ru.smak.ui

import ru.smak.ui.painting.CartesianPainter
import ru.smak.ui.painting.CartesianPlane
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.beans.PropertyChangeListener
import javax.swing.*

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

        val mainPlane = CartesianPlane(
            xMinM.value as Double,
            xMaxM.value as Double,
            yMinM.value as Double,
            yMaxM.value as Double
        )

        val cartesianPainter = CartesianPainter(mainPlane)

        mainPanel = GraphicsPanel(cartesianPainter).apply {
            background = Color.WHITE
        }
        mainPlane.pixelSize = mainPanel.size

        mainPanel.addComponentListener(object: ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                mainPlane.pixelSize = mainPanel.size
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

        xMin.addChangeListener{
            xMaxM.minimum = xMin.value as Double + 0.1
            mainPlane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        xMax.addChangeListener{
            xMinM.maximum = xMax.value as Double - 0.1
            mainPlane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        yMin.addChangeListener{
            yMaxM.minimum = yMin.value as Double + 0.1
            mainPlane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }
        yMax.addChangeListener{
            yMinM.maximum = yMax.value as Double - 0.1
            mainPlane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
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
        mainPlane.width = mainPanel.width
        mainPlane.height = mainPanel.height
    }
}